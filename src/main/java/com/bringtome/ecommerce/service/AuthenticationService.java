package com.bringtome.ecommerce.service;

import com.bringtome.ecommerce.entity.UserEntity;
import com.bringtome.ecommerce.security.oauth2.OAuth2UserInfo;

import java.util.Map;

public interface AuthenticationService {

    Map<String, Object> login(String email, String password);

    String registerUser(UserEntity user, String captcha, String password2);

    UserEntity registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

    UserEntity updateOauth2User(UserEntity user, String provider, OAuth2UserInfo oAuth2UserInfo);

    String activateUser(String code);

    String getEmailByPasswordResetCode(String code);

    String sendPasswordResetCode(String email);

    String passwordReset(String email, String password, String password2);
}
