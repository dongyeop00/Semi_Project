package com.gml.semi_project.DTO;

import com.gml.semi_project.Entity.User;
import com.gml.semi_project.Enum.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJoinRequest {

    private String memberId;
    private String password;
    private String passwordCheck;
    private String nickname;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .memberId(memberId)
                .password(encodedPassword)
                .nickname(nickname)
                .userRole(UserRole.NEWBIE)
                .createdAt(LocalDateTime.now())
                .receivedLikeCnt(0)
                .build();
    }
}