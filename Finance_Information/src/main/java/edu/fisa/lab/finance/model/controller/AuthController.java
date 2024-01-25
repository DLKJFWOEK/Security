package edu.fisa.lab.finance.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.fisa.lab.finance.model.dto.RegistrationRequest;
import edu.fisa.lab.finance.client.service.ClientService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private ClientService ClientService;
	
	public AuthController(ClientService clientService) {
		this.ClientService = clientService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerClient(@Validated @RequestBody RegistrationRequest request){
		ClientService.registerClient(request.getName(), request.getId(), request.getPassword());
		return ResponseEntity.ok("회원가입이 완료되었습니다.");
	}
}
