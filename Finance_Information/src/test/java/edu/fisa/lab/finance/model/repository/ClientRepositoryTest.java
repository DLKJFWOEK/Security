package edu.fisa.lab.finance.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import edu.fisa.lab.finance.model.entity.Client;


@DataJpaTest
@Sql({"/data.sql"})
public class ClientRepositoryTest {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	public void testFindById() {
		
		Client client = new Client();
		client.setName("이강진");
		client.setId("frog4821");
		client.setPassword("securepassword");
		clientRepository.save(client);
		
		Optional<Client> foundClient = clientRepository.findById("이강진");
		
		assertTrue(foundClient.isPresent());
		assertEquals("이강진", foundClient.get().getName());
		
	}
}
