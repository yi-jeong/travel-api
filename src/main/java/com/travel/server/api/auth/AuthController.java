package com.travel.server.api.auth;

import com.travel.server.exception.dto.CommonResponse;
import com.travel.server.api.auth.dto.JwtResponse;
import com.travel.server.api.auth.dto.LoginRequest;
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
        JwtResponse result = authService.login(request);

        return CommonResponse.success(result);
    }

    @PostMapping("/join")
    public CommonResponse<JwtResponse> signup(@RequestBody LoginRequest request) {
        JwtResponse result = authService.join(request);

        return CommonResponse.success(result);
    }

}
