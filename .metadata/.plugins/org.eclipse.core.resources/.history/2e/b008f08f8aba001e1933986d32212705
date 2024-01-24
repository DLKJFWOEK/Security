package edu.fisa.lab.finance.model.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import edu.fisa.lab.finance.model.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest {
	
	@Autowired
	private ClientRepository clientRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	private Client client;

	@BeforeEach
	void setUp(){

		client =Client.builder()
			.name("이강진")
			.id("frog4821").password("securepassword")
			.build();


	}


	@DisplayName("회원 저장 테스트")
	@Test
	void testFindById() {

		//given
		entityManager.persist(client);


		//when
		Client savedClient = clientRepository.save(client);


		//then
		Assertions.assertThat(savedClient.getId()).isEqualTo(1L);
		
	}
}
