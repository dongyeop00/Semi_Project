package com.gml.semi_project.DTO;

import com.gml.semi_project.Entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String memberId;
    private String nickname;
    private String nowPassword;
    private String newPassword;
    private String newPasswordCheck;

    public static UserDTO of(User user) {
        return UserDTO.builder()
                .memberId(user.getMemberId())
                .nickname(user.getNickname())
                .build();
    }
}