package com.example.travel.services;

import com.example.travel.exception.CustomException;
import com.example.travel.models.Member;
import com.example.travel.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return memberRepository.findByUserId(username)
                        .orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다."));
            }
        };
    }

    public Member getMe(String username){
        return memberRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("회원이 존재하지 않습니다."));
    }

    public Member save(Member newMember) {
        Optional<Member> sameCheck = memberRepository.findByUserId(newMember.getUserId());
        if(sameCheck.isPresent()){
            throw new CustomException("Error", HttpStatus.UNPROCESSABLE_ENTITY, "중복된 아이디가 있습니다.");
        }

        newMember.setUpdatedAt(LocalDateTime.now());
        return memberRepository.save(newMember);
    }
}
