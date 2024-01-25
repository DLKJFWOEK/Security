package edu.fisa.lab.finance.client.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import edu.fisa.lab.finance.client.domain.dto.ClientDto;
import edu.fisa.lab.finance.client.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/client")
	public String insertClient(ClientDto clientDto, HttpServletRequest request)throws ServletException, IOException{
		Long clientNo = clientService.insertClient(clientDto);
		String name = request.getParameter("name");
		
		HttpSession session = request.getSession();
		session.setAttribute("clientNo", clientNo);
		session.setAttribute("name", name);
		return "redirect:/main.html";
	}
	
	@ExceptionHandler
	public String exceptionHandler(Exception e, Model m) {
		m.addAttribute("errorMsg", "발생된 이슈" + e.getMessage());
		e.printStackTrace();
		return "forward:showError.jsp";
	}	
}
