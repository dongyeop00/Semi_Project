package com.gml.semi_project.Entity;

import com.gml.semi_project.Enum.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId; // 아이디
    private String memberPassword; // 비밀번호
    private String memberNickname; // 닉네임
    private LocalDateTime createAt; // 만든 시간
    private Integer receivedWarningCnt; // 받은 신고 수
    private Integer receivedLikeCnt; // 받은 좋아요 수

    @Enumerated(EnumType.STRING)
    private UserRole userRole; // 권한

    /*
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Like> likes; // 유저가 누른 좋아요

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Comment> comments;
     */

}
