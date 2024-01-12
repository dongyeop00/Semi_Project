package com.gml.semi_project.Service;

import com.gml.semi_project.DTO.MemberDTO;
import com.gml.semi_project.Entity.MemberEntity;
import com.gml.semi_project.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(MemberDTO memberDTO){
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDto) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<MemberEntity> byMemberID =  memberRepository.findByMemberID(memberDto.getMemberID());
        if(byMemberID.isPresent()){
            // 조회결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byMemberID.get();
            if(memberEntity.getMemberPassword().equals(memberDto.getMemberPassword())){
                //비밀번호가 일치하는 경우
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }

    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if(byMemberEmail.isPresent()){
            return null;
        }
        else{
            return "ok";
        }
    }

    public String idCheck(String memberID) {
        Optional<MemberEntity> byMemberID = memberRepository.findByMemberID(memberID);
        if(byMemberID.isPresent()){
            return null;
        }
        else{
            return "ok";
        }
    }

    public String nicknameCheck(String memberNickname) {
        Optional<MemberEntity> byMemberNickname = memberRepository.findByMemberNickname(memberNickname);
        if(byMemberNickname.isPresent()){
            return null;
        }
        else{
            return "ok";
        }
    }
}
