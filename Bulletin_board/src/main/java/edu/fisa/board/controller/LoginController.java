package edu.fisa.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fisa.board.service.AuthenticationService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {



	private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }



    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
    	System.out.println("0000 ---  " + username + " " + password);
    	
    	if(authenticationService.isValidCredentials(username, password)) {
    		
    		return "redirect:/board/list";
        } else {
            
            model.addAttribute("error", "자격 증명에 실패하였습니다.");
            return "login";
        }
    }
}