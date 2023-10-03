package com.example.travel.dto;

import com.example.travel.models.Role;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
