package com.travel.server.api.auth;

import com.travel.server.api.member.MemberRepository;
import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.api.member.model.Member;
import com.travel.server.exception.CustomException;
import com.travel.server.exception.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws CustomException {
        return memberRepository.findByUserId(username)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_USER_NOT_FOUND));
    }

}
