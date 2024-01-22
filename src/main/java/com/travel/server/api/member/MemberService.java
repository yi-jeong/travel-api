package com.travel.server.api.member;

import com.travel.server.exception.CustomException;
import com.travel.server.exception.model.ErrorCode;
import com.travel.server.api.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return memberRepository.findByUserId(username)
                        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_USER_NOT_FOUND));
            }
        };
    }

    public Member getMe(String username){
        return memberRepository.findByUserId(username).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_USER_NOT_FOUND));
    }

    public Member save(Member newMember) {
        Optional<Member> sameCheck = memberRepository.findByUserId(newMember.getUserId());

        if(sameCheck.isPresent()){
            throw new CustomException(ErrorCode.MEMBER_DUPLICATE_LOGIN_ID);
        }

        newMember.setUpdatedAt(LocalDateTime.now());
        return memberRepository.save(newMember);
    }
}
