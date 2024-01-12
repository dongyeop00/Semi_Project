package com.gml.semi_project.Entity;

import com.gml.semi_project.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberID;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column(unique = true)
    private String memberNickname;

    public static MemberEntity toMemberEntity(MemberDTO memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberID(memberDto.getMemberID());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberNickname(memberDto.getMemberNickname());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setMemberID(memberDto.getMemberID());
        memberEntity.setMemberEmail(memberDto.getMemberEmail());
        memberEntity.setMemberPassword(memberDto.getMemberPassword());
        memberEntity.setMemberNickname(memberDto.getMemberNickname());
        return memberEntity;
    }
}
