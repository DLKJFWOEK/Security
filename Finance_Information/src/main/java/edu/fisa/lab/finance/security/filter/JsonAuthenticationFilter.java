package edu.fisa.lab.finance.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


	private final static String DEFAULT_LOGIN_URL ="/auth/login";


	public JsonAuthenticationFilter() {
		super(DEFAULT_LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException, IOException, ServletException {

		String loginId = request.getParameter("loginId");
		String pwd = request.getParameter("pwd");

		if (loginId.isBlank()|| loginId.isEmpty() || pwd.isEmpty() || pwd.isBlank()){
			throw new BadCredentialsException("로그인 정보가 올바르지 않습니다 확인해주세요.");
		}


		return UsernamePasswordAuthenticationToken.unauthenticated(loginId, pwd);
	}
}
