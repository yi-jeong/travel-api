package com.example.travel.repositories;

import com.example.travel.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberDto> searchAllMember(MemberDto memberDto);
}
