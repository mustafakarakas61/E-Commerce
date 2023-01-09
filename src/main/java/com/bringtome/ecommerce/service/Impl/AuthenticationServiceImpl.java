package com.bringtome.ecommerce.service.Impl;

import com.bringtome.ecommerce.config.EmailConfig;
import com.bringtome.ecommerce.entity.UserEntity;
import com.bringtome.ecommerce.dto.CaptchaResponse;
import com.bringtome.ecommerce.enums.AuthProviderEnum;
import com.bringtome.ecommerce.enums.RoleEnum;
import com.bringtome.ecommerce.exception.ApiRequestException;
import com.bringtome.ecommerce.exception.EmailException;
import com.bringtome.ecommerce.exception.PasswordConfirmationException;
import com.bringtome.ecommerce.exception.PasswordException;
import com.bringtome.ecommerce.repository.UserRepository;
import com.bringtome.ecommerce.security.JwtProvider;
import com.bringtome.ecommerce.security.oauth2.OAuth2UserInfo;
import com.bringtome.ecommerce.service.AuthenticationService;
import com.bringtome.ecommerce.service.email.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final RestTemplate restTemplate;
    private final JwtProvider jwtProvider;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, RestTemplate restTemplate, JwtProvider jwtProvider, MailSender mailSender, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.restTemplate = restTemplate;
        this.jwtProvider = jwtProvider;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Value("${hostname}")
    private String hostname;

    @Value("${recaptcha.secret}")
    private String secret;

    @Value("${recaptcha.url}")
    private String captchaUrl;

    @Override
    public Map<String, Object> login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ApiRequestException("Email bulunamadı.", HttpStatus.NOT_FOUND));
            String userRole = user.getRoles().iterator().next().name();
                String token = jwtProvider.createToken(email, userRole);
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("token", token);
            return response;
        } catch (AuthenticationException e) {
            throw new ApiRequestException("Geçersiz email veya şifre", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    @Transactional
    public String registerUser(UserEntity user, String captcha, String password2) {
        String url = String.format(captchaUrl, secret, captcha);
        restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponse.class);

        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            throw new PasswordException("Şifre eşleşmiyor.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailException("Bu email zaten mevcut.");
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setProvider(AuthProviderEnum.LOCAL);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        sendEmail(user, "Activation code", "registration-template", "registrationUrl", "/activate/" + user.getActivationCode());
        return "Kullanıcı başarıyla kayıt oldu.";
    }

    @Override
    @Transactional
    public UserEntity registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo) {
        UserEntity user = new UserEntity();
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setProvider(AuthProviderEnum.valueOf(provider.toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserEntity updateOauth2User(UserEntity user, String provider, OAuth2UserInfo oAuth2UserInfo) {
        user.setFirstName(oAuth2UserInfo.getFirstName());
        user.setLastName(oAuth2UserInfo.getLastName());
        user.setProvider(AuthProviderEnum.valueOf(provider.toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    public String getEmailByPasswordResetCode(String code) {
        return userRepository.getEmailByPasswordResetCode(code)
                .orElseThrow(() -> new ApiRequestException("Şifre değiştirme kodu geçersiz!", HttpStatus.BAD_REQUEST));
    }

    @Override
    @Transactional
    public String sendPasswordResetCode(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email bulunamadı.", HttpStatus.NOT_FOUND));
        user.setPasswordResetCode(UUID.randomUUID().toString());
        userRepository.save(user);

        sendEmail(user, "Password reset", "password-reset-template", "resetUrl", "/reset/" + user.getPasswordResetCode());
        return "Şifre değiştirme kodunuz mailinize gönderildi.";
    }

    @Override
    @Transactional
    public String passwordReset(String email, String password, String password2) {
        if (StringUtils.isEmpty(password2)) {
            throw new PasswordConfirmationException("Şifre onaylama kısmı boş olamaz.");
        }
        if (password != null && !password.equals(password2)) {
            throw new PasswordException("Şifreniz uyuşmuyor.");
        }
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email bulunamadı.", HttpStatus.NOT_FOUND));
        user.setPassword(passwordEncoder.encode(password));
        user.setPasswordResetCode(null);
        userRepository.save(user);
        return "Şifreniz başarıyla değiştirildi!";
    }

    @Override
    @Transactional
    public String activateUser(String code) {
        UserEntity user = userRepository.findByActivationCode(code)
                .orElseThrow(() -> new ApiRequestException("Aktivasyon kodu bulunamadı.", HttpStatus.NOT_FOUND));
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return "Kullanıcı başarıyla aktif oldu.";
    }

    private void sendEmail(UserEntity user, String subject, String template, String urlAttribute, String urlPath) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("firstName", user.getFirstName());
        attributes.put(urlAttribute, "http://" + hostname + urlPath);
        mailSender.sendMessageHtml(user.getEmail(), subject,template, attributes);
    }
}
