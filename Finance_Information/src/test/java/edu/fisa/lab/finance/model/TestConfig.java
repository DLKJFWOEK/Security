package edu.fisa.lab.finance.model;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.fisa.lab.finance.client.repository.ClientRepository;
import edu.fisa.lab.finance.client.service.ClientService;

@TestConfiguration
public class TestConfig {
	
	@Bean
	public ClientService clientService() {
		return Mockito.mock(ClientService.class);
	}
	
	@Bean
	public ClientRepository clientRepository() {
		return Mockito.mock(ClientRepository.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return Mockito.mock(PasswordEncoder.class);
	
	}
}
