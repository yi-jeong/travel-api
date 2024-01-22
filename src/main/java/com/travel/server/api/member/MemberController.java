package com.travel.server.api.member;

import com.travel.server.api.auth.LoginUser;
import com.travel.server.exception.dto.CommonResponse;
import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.api.member.model.Member;
import com.travel.server.utils.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    
    private final MemberService memberService;

    /**
     * 내 정보 조회
     * @return
     */
    @GetMapping("/me")
    public CommonResponse<MemberDto> getCurrentMember(
            @LoginUser MemberDto memberDto
    ){

        log.info("=== 내 정보 조회 getCurrentMember member: {}", memberDto);

        return CommonResponse.success(memberDto);
    }

    /**
     * 유저 정보 리스트 조회
     * @param userId
     * @param nickName
     * @return
     */
    @GetMapping("/list")
    public CommonResponse<List<MemberDto>> getMemberList(
            @RequestParam(value="userId", required = false) String userId,
            @RequestParam(value="nickName", required = false) String nickName
    ){

        log.info("=== 유저 정보 리스트 조회 getMemberList userId: {}, nickName: {}", userId, nickName);

        List<MemberDto> resultList = memberService.getMemberList(userId, nickName);

        return CommonResponse.success(resultList);
    }

}
