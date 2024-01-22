package com.travel.server.api.member;

import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.exception.CustomException;
import com.travel.server.exception.model.ErrorCode;
import com.travel.server.api.member.model.Member;
import com.travel.server.utils.DataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    public Member save(Member newMember) {
        Optional<Member> sameCheck = memberRepository.findByUserId(newMember.getUserId());

        if(sameCheck.isPresent()){
            throw new CustomException(ErrorCode.MEMBER_DUPLICATE_LOGIN_ID);
        }

        return memberRepository.save(newMember);
    }

    public List<MemberDto> getMemberList(String userId, String nickName){
        return memberRepository.searchAllMember(userId, nickName);
    }
}
