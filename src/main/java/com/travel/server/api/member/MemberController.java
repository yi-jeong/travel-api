package com.travel.server.api.member;

import com.travel.server.exception.dto.CommonResponse;
import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.api.member.model.Member;
import com.travel.server.utils.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/list")
    public CommonResponse<List<MemberDto>> getMemberList(@RequestParam(value="userId", required = false) String UserId, @RequestParam(value="nickName", required = false) String NickName){
        MemberDto member = new MemberDto();

        if(UserId != null){
            member.setUserId(UserId);
        }

        if(NickName != null){
            member.setNickName(NickName);
        }

        List<MemberDto> memberInfo = memberRepository.searchAllMember(member);

        return CommonResponse.success(memberInfo);
    }

}
