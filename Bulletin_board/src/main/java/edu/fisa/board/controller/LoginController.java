package edu.fisa.board.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 이 메서드는 실제로 로그인을 처리하게 됩니다.
//    @PostMapping("/login")
//    public String login(Model model) {
//        // 로그인 처리 로직을 여기에 추가
//    	// 로그인 성공 시에만 리디렉션 수행
//        return "redirect:/board/list"; // 로그인 성공 후의 경로
//    }
    
    @PostMapping("/login")
    public String login() {
        // 로그인 처리 로직을 여기에 추가

        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증된 사용자가 존재하면 리디렉션 수행
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            return "redirect:/board/list"; // 로그인 성공 후의 경로
        } else {
            // 로그인 실패 시 로그인 페이지로 이동
            return "login";
        }
    }
}