package com.travel.server.api.member.dto;

import com.travel.server.api.member.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private long serial;
    private String userId;
    private String nickName;
    private String phoneNumber;
    private String email;

    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String del;
}
