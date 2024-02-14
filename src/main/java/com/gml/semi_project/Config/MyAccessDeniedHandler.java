package com.gml.semi_project.Config;

import com.gml.semi_project.Repository.UserRepository;
import com.gml.semi_project.Entity.User;
import com.gml.semi_project.Enum.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    private final UserRepository userRepository;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loginUser = null;
        if(auth != null){
            loginUser = userRepository.findByMemberId(auth.getName()).get();
        }
        String requestURI = request.getRequestURI();

        if(requestURI.contains("/users/login") || requestURI.contains("/users/join")){
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('이미 로그인 되어있습니다!');location.href='/';</script>");
            pw.flush();
        }
        else if (requestURI.contains("vip")) {
            // 메세지 출력 후 홈으로 redirect
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('VIP 등급 이상의 유저만 접근 가능합니다!'); location.href='/';</script>");
            pw.flush();
        }
        else  if (loginUser != null && loginUser.getUserRole().equals(UserRole.BLACKLIST)){
            // 메세지 출력 후 홈으로 redirect
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('블랙리스트는 글, 댓글 작성이 불가능합니다.'); location.href='/';</script>");
            pw.flush();
        }
        else if (requestURI.contains("free/write")) {
            // 메세지 출력 후 홈으로 redirect
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('가입인사 작성 후 작성 가능합니다!'); location.href='/boards/greeting';</script>");
            pw.flush();
        }
        else if (requestURI.contains("greeting")) {
            // 메세지 출력 후 홈으로 redirect
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('가입인사는 한 번만 작성 가능합니다!'); location.href='/boards/greeting';</script>");
            pw.flush();
        }
        else if (requestURI.contains("admin")) {
            // 메세지 출력 후 홈으로 redirect
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<script>alert('관리자만 접속 가능합니다!'); location.href='/';</script>");
            pw.flush();

        }
    }
}
