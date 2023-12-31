package com.example.travel.services;

import com.example.travel.config.JwtTokenProvider;
import com.example.travel.dto.auth.JwtResponse;
import com.example.travel.dto.auth.LoginRequest;
import com.example.travel.exception.CustomException;
import com.example.travel.models.ErrorCode;
import com.example.travel.models.Member;
import com.example.travel.repositories.MemberRepository;
import com.example.travel.models.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public JwtResponse join(LoginRequest request) {
        var member = Member
                .builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        member = memberService.save(member);
        var jwt = jwtTokenProvider.createToken(new HashMap<>(), member);
        return JwtResponse.builder().token(jwt).build();
    }

    public JwtResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword()));
        } catch (AuthenticationException ex) {
            throw new CustomException(ErrorCode.MEMBER_LOGIN_INFO_NOT_FOUND);
        }

        var member = memberRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_LOGIN_INFO_NOT_FOUND));

        var jwt = jwtTokenProvider.createToken(new HashMap<>(), member);
        return JwtResponse.builder().token(jwt).build();
    }

}
