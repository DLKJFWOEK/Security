package edu.fisa.board.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean isValidCredentials(String username, String password) {
		
		return true;
	}
}
