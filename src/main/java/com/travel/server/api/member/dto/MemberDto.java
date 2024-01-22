package com.travel.server.api.member.dto;

import com.travel.server.api.member.model.Member;
import com.travel.server.api.member.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class MemberDto {
    private long id;
    private String userId;
    private String nickName;
    private String phoneNumber;
    private String email;

    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberDto from(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .nickName(member.getNickName())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .role(member.getRole())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
