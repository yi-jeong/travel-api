package com.travel.server.api.auth;

import com.travel.server.api.member.MemberService;
import com.travel.server.config.auth.JwtTokenProvider;
import com.travel.server.api.auth.dto.JwtResponse;
import com.travel.server.api.auth.dto.LoginRequest;
import com.travel.server.exception.CustomException;
import com.travel.server.exception.model.ErrorCode;
import com.travel.server.api.member.model.Member;
import com.travel.server.api.member.MemberRepository;
import com.travel.server.api.member.model.Role;
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
        // 유저 정보 인증
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword()));

        var member = memberRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_LOGIN_INFO_NOT_FOUND));

        var jwt = jwtTokenProvider.createToken(new HashMap<>(), member);
        return JwtResponse.builder().token(jwt).build();
    }

}
