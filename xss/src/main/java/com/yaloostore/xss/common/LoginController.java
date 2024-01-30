package com.yaloostore.xss.common;


import com.yaloostore.xss.client.domain.dto.ClientDTO;
import com.yaloostore.xss.client.domain.dto.LoginRequestDto;
import com.yaloostore.xss.client.service.ClientService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final ClientService clientService;


    @GetMapping("/")
    public String loginForm(Model model, HttpServletRequest request, HttpServletResponse response){

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        model.addAttribute("loginRequestDto", new LoginRequestDto());

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginId")) {
                    log.info("여기가 오긴 허냐?");
                    return "redirect:/boards/lists";
                }
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequestDto") LoginRequestDto loginRequestDto,
                        Model model, HttpServletResponse response, HttpServletRequest request) {
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        model.addAttribute("loginRequestDto", loginRequestDto);
        log.info(loginRequestDto.getLoginId());
        ClientDTO dto = clientService.isAuthentication(loginRequestDto);

        if (Objects.isNull(dto)){
            model.addAttribute("errorMessage", "아이디와 비밀번호를 다시 확인해주세요.");
            return "login";
        }

        model.addAttribute("dto", dto);

        Cookie loginId = new Cookie("loginId", dto.getLoginId());
        Cookie pwd = new Cookie("pwd", dto.getPwd());
        Cookie clientId = new Cookie("clientId", dto.getLoginId());

        loginId.setMaxAge(900000);
        pwd.setMaxAge(900000);
        clientId.setMaxAge(900000);

        response.addCookie(loginId);
        response.addCookie(pwd);
        response.addCookie(clientId);


        return "redirect:/boards/lists";

    }


}
