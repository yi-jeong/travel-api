package com.travel.server.api.travel;

import com.travel.server.api.auth.LoginUser;
import com.travel.server.api.member.dto.MemberDto;
import com.travel.server.api.travel.dto.TravelResponseDto;
import com.travel.server.exception.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/travel")
public class TravelController {


    private final TravelService travelService;

    /**
     * 여행지 리스트 조회
     * @return
     */
    @GetMapping("/list")
    public CommonResponse<Page<TravelResponseDto>> getTravelList(
            @LoginUser MemberDto memberDto,
            @PageableDefault(page = 0, size = 10, sort = "serial", direction = Sort.Direction.DESC) Pageable pageable
    ){

        log.info("=== 여행지 리스트 조회 getMemberList");

        Page<TravelResponseDto> resultList = travelService.getTravelList(memberDto.getId(), pageable);

        log.info("=== END 여행지 리스트 조회 getMemberList");

        return CommonResponse.success(resultList);
    }
}
