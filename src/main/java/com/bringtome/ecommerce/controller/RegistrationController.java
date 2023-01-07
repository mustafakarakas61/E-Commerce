package com.bringtome.ecommerce.controller;

import com.bringtome.ecommerce.dto.RegistrationRequest;
import com.bringtome.ecommerce.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final AuthenticationMapper authenticationMapper;

    @Autowired
    public RegistrationController(AuthenticationMapper authenticationMapper) {
        this.authenticationMapper = authenticationMapper;
    }

    @PostMapping
    public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest user, BindingResult bindingResult) {
        return ResponseEntity.ok(authenticationMapper.registerUser(user.getCaptcha(), user, bindingResult));
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateEmailCode(@PathVariable String code) {
        return ResponseEntity.ok(authenticationMapper.activateUser(code));
    }
}
