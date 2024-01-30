package com.yaloostore.sqlinjection.client.controller;

import java.util.Objects;
import java.util.Optional;

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
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginUser")) {
					return "board/list";
				}
			}
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
		ClientDTO dto = clientDAO.findClientByLoginId_pre(loginRequestDto.getLoginId(), loginRequestDto.getPwd());

		if (Objects.isNull(dto)){
			model.addAttribute("errorMessage", "아이디와 비밀번호를 다시 확인해주세요.");
			return "login";
		}
		model.addAttribute("dto", dto);


		Cookie loginUser =new Cookie("loginUser", dto.getLoginId());
		response.addCookie(loginUser);
		model.addAttribute("loginUser", dto.getLoginId());


		return "board/list";


	}

	@PostMapping("/non-sql-injection/login")
	public String login_protect(@ModelAttribute("loginRequestDto") LoginRequestDto loginRequestDto,
						Model model, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("servletContext", request.getServletContext());

		model.addAttribute("loginRequestDto", loginRequestDto);
		ClientDTO dto = clientDAO.findClientByLoginId_pre(loginRequestDto.getLoginId(), loginRequestDto.getPwd());

		if (Objects.isNull(dto)){
			model.addAttribute("errorMessage", "아이디와 비밀번호를 다시 확인해주세요.");
			return "login";
		}
		model.addAttribute("dto", dto);


		Cookie loginUser =new Cookie("loginUser", dto.getLoginId());
		response.addCookie(loginUser);
		model.addAttribute("loginUser", dto.getLoginId());


		return "board/list";


	}



}
