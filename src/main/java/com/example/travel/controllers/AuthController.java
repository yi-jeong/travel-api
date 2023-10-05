package com.example.travel.controllers;

import com.example.travel.dto.common.CommonResponse;
import com.example.travel.services.AuthService;
import com.example.travel.dto.auth.JwtResponse;
import com.example.travel.dto.auth.LoginRequest;
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
    public CommonResponse<JwtResponse> login(@RequestBody LoginRequest request) {
        log.debug("login 진입 {}", request.toString());
        JwtResponse result = authService.login(request);

        return CommonResponse.success(result);
    }

    @PostMapping("/join")
    public CommonResponse<JwtResponse> signup(@RequestBody LoginRequest request) {
        log.debug("join 진입 {}", request);
        JwtResponse result = authService.join(request);

        return CommonResponse.success(result);
    }

}
