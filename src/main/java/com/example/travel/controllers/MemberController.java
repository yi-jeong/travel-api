package com.example.travel.controllers;

import com.example.travel.dto.common.CommonResponse;
import com.example.travel.dto.MemberDto;
import com.example.travel.models.Member;
import com.example.travel.repositories.MemberRepository;
import com.example.travel.services.MemberService;
import com.example.travel.utils.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/me")
    public CommonResponse<MemberDto> getCurrentMember(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : " + authentication.getPrincipal().toString());

        Member memberInfo = memberService.getMe(authentication.getName());
        MemberDto member = DataMapper.map(memberInfo, MemberDto.class);

        return CommonResponse.success(member);
    }

}
