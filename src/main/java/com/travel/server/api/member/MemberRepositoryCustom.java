package com.travel.server.api.member;

import com.travel.server.api.member.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberDto> searchAllMember(MemberDto memberDto);
}
