package com.gml.semi_project.Controller;

import com.gml.semi_project.DTO.MemberDTO;
import com.gml.semi_project.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/register")
    public String registerForm(){return "register";};
    @PostMapping("/member/register")
    public String register(@ModelAttribute MemberDTO memberDTO){
        memberService.register(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){return "login";}


    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO); // 회원정보가 맞는지 확인
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            System.out.println("로그인 성공");

            return "redirect:/";
        } else {
            System.out.println("로그인 실패");
            return "login";
        }
    }


    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        String checkEmail = memberService.emailCheck(memberEmail);
        return checkEmail;
    }

    @PostMapping("/member/id-check")
    public @ResponseBody String idCheck(@RequestParam("memberID") String memberID){
        String checkID = memberService.idCheck(memberID);
        return checkID;
    }

    @PostMapping("/member/nickname-check")
    public @ResponseBody String nicknameCheck(@RequestParam("memberNickname") String memberNickname){
        String checkNickname = memberService.idCheck(memberNickname);
        return checkNickname;
    }


}
