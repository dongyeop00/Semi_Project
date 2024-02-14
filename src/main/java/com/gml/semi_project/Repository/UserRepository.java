package com.gml.semi_project.Repository;

import com.gml.semi_project.Entity.User;
import com.gml.semi_project.Enum.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMemberId(String memberID);

    //관리자가 닉네임 검색시사용
    Page<User> findAllByNicknameContains(String nickname, PageRequest pageRequest);

    //중복체크용
    Boolean existsByMemberId(String memberID);

    Boolean existsByNickname(String nickname);

    Long countAllByUserRole(UserRole userRole);

    //Optional<User> findByMemberEmail(String memberEmail);

    //Optional<User> findByMemberNickname(String memberNickname);
}


