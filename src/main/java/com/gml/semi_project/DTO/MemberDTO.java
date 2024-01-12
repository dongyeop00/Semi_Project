package com.gml.semi_project.DTO;

import com.gml.semi_project.Entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    public String MemberID;
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberNickname;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberID(memberDTO.getMemberID());
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberPassword(memberDTO.getMemberPassword());
        memberDTO.setMemberEmail(memberDTO.getMemberEmail());
        memberDTO.setMemberNickname(memberDTO.getMemberNickname());
        return memberDTO;
    }
}
