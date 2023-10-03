package com.example.travel.controllers;

import com.example.travel.services.AuthService;
import com.example.travel.dto.JwtResponse;
import com.example.travel.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        log.debug("login 진입 {}", request.toString());
        return authService.login(request);
    }

    @PostMapping("/join")
    public JwtResponse signup(@RequestBody LoginRequest request) {
        log.debug("join 진입 {}", request);
        return authService.join(request);
    }

}
