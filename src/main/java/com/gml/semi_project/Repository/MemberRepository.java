package com.gml.semi_project.Repository;


import com.gml.semi_project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByMemberID(String memberID);

    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    Optional<MemberEntity> findByMemberNickname(String memberNickname);
}
