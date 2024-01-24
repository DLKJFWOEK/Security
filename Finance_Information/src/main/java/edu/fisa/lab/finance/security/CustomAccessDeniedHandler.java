package edu.fisa.lab.finance.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
						AccessDeniedException ex) throws IOException, ServletException{
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: " + ex.getMessage());
	}
}
