package edu.fisa.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fisa.board.service.AuthenticationService;

@Controller
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;
	
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
    public String login(@RequestParam String username, @RequestParam String password, Model model){
    	System.out.println("0000 ---  " + username + " " + password);
    	// 사용자 자격 증명 검증 로직
    	if(authenticationService.isValidCredentials(username, password)) {
    		//로그인 성공
    		System.out.println("++++++++++++++");
    		return "redirect:layout";   //?
        } else {
            // 로그인 실패 시 로그인 페이지로 이동
            model.addAttribute("error", "자격 증명에 실패하였습니다.");
            return "redirect:login";
        }
    }
}