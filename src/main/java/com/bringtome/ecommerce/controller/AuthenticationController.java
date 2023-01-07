package com.bringtome.ecommerce.controller;

import com.bringtome.ecommerce.dto.PasswordResetRequest;
import com.bringtome.ecommerce.dto.auth.AuthenticationRequest;
import com.bringtome.ecommerce.dto.auth.AuthenticationResponse;
import com.bringtome.ecommerce.mapper.AuthenticationMapper;
import com.bringtome.ecommerce.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationMapper authenticationMapper;

    @Autowired
    public AuthenticationController(AuthenticationMapper authenticationMapper) {
        this.authenticationMapper = authenticationMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationMapper.login(request));
    }

    @GetMapping("/forgot/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) {
        return ResponseEntity.ok(authenticationMapper.sendPasswordResetCode(email));
    }

    @GetMapping("/reset/{code}")
    public ResponseEntity<String> getEmailByPasswordResetCode(@PathVariable String code) {
        return ResponseEntity.ok(authenticationMapper.getEmailByPasswordResetCode(code));
    }

    @PostMapping("/reset")
    public ResponseEntity<String> passwordReset(@RequestBody PasswordResetRequest passwordReset) {
        return ResponseEntity.ok(authenticationMapper.passwordReset(passwordReset.getEmail(), passwordReset));
    }

    @PutMapping("/edit/password")
    public ResponseEntity<String> updateUserPassword(@AuthenticationPrincipal UserPrincipal user,
                                                     @Valid @RequestBody PasswordResetRequest passwordReset,
                                                     BindingResult bindingResult) {
        return ResponseEntity.ok(authenticationMapper.passwordReset(user.getEmail(), passwordReset, bindingResult));
    }
}
