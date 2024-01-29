package com.yaloostore.sqlinjection.client.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yaloostore.sqlinjection.client.domain.dto.ClientDTO;
import com.yaloostore.sqlinjection.client.domain.dto.LoginRequestDto;
import com.yaloostore.sqlinjection.client.persistence.ClientDAO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ClientController {

	private final ClientDAO clientDAO;
	@GetMapping("/")
	public String loginForm(Model model, HttpServletRequest request, HttpServletResponse response){

		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("servletContext", request.getServletContext());
		Cookie[] cookies = request.getCookies();
		//만약 로그인에 성공한 유저라면 쿠키값이 있기 때문에 다른 곳으로 보낸다.
		if (!Objects.isNull(cookies[0])){
			Cookie loginUser = cookies[0];
			model.addAttribute("loginUser", loginUser.getValue());
			return "board/list";
		}

		model.addAttribute("loginRequestDto", new LoginRequestDto());

		return "login";
	}

	@PostMapping("/sql-injection/login")
	public String login(@ModelAttribute("loginRequestDto") LoginRequestDto loginRequestDto,
		Model model, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("servletContext", request.getServletContext());

		model.addAttribute("loginRequestDto", loginRequestDto);
		ClientDTO dto = clientDAO.findClientByLoginId(loginRequestDto.getLoginId());

		model.addAttribute("dto", dto);
		log.info("여기까지 들어왔지요");
		Cookie loginUser =new Cookie("loginUser", dto.getLoginId());
		response.addCookie(loginUser);
		model.addAttribute("loginUser", dto.getLoginId());


		return "board/list";


	}

}
