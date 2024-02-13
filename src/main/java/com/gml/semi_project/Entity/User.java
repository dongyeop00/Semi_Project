package com.gml.semi_project.Entity;

import com.gml.semi_project.Enum.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String Password; // 비밀번호
    private String Nickname; // 닉네임
    private LocalDateTime createAt; // 만든 시간
    private Integer receivedWarningCnt; // 받은 신고 수
    private Integer receivedLikeCnt; // 받은 좋아요 수

    @Enumerated(EnumType.STRING)
    private UserRole userRole; // 권한

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Like> likes; // 유저가 누른 좋아요

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Comment> comments;

    public void rankUp(UserRole userRole, Authentication auth) {
        this.userRole = userRole;

        // 현재 저장되어 있는 Authentication 수정 => 재로그인 하지 않아도 권한 수정 되기 위함
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        updatedAuthorities.add(new SimpleGrantedAuthority(userRole.name()));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public void likeChange(Integer receivedLikeCnt) {
        this.receivedLikeCnt = receivedLikeCnt;
        //좋아요 10개 이상 받으면 vip
        if (this.receivedLikeCnt >= 10 && this.userRole.equals(UserRole.NEWBIE)) {
            this.userRole = UserRole.VIP;
        }
    }

    public void edit(String newPassword, String newNickname) {
        this.password = newPassword;
        this.nickname = newNickname;
    }

    public void changeRole() {
        if (userRole.equals(UserRole.NEWBIE)) userRole = UserRole.VIP;
        else if (userRole.equals(UserRole.VIP)) userRole = UserRole.BLACKLIST;
        else if (userRole.equals(UserRole.BLACKLIST)) userRole = UserRole.NEWBIE;
    }

}
