package edu.fisa.lab.finance.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.fisa.lab.finance.client.domain.dto.ClientDto;
import edu.fisa.lab.finance.client.domain.entity.Client;
import edu.fisa.lab.finance.client.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
		this.clientRepository = clientRepository;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	public void registerClient(String name, String id, String password) {
		if(clientRepository.findById(id).isPresent()) {
			throw new IllegalArgumentException("이미 등록된 아이디입니다.");
		}
		
		Client newClient = new Client();
		newClient.setName(name);
		newClient.setLoginId(id);
		newClient.setPassword(passwordEncoder.encode(password));
		
		clientRepository.save(newClient);
	}
	
	/////// 위까지 회원가입 코드 /////////
	
	@Transactional
	public Long insertClient(ClientDto clientDto) {
		
		Client c = clientDto.toEntity(clientDto);
		clientRepository.save(c);
		return c.getClientId();
	}
	
	@ExceptionHandler
	public String exceptionHandler(Exception e, Model m) {
		m.addAttribute("errorMsg", "발생된 이슈 " + e.getMessage());
		e.printStackTrace();
		return "forward:showError.jsp";
	}
}
	
