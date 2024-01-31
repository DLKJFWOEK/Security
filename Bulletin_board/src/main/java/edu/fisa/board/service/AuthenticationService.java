package edu.fisa.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.fisa.board.client.domain.entity.Client;
import edu.fisa.board.client.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final ClientRepository clientRepository;

	public boolean isValidCredentials(String username, String password) {

		Optional<Client> op = clientRepository.findByLoginId(username);

		if (op.isPresent()) {

			Client client = op.get();

			if (client.getPwd().equals(password) && client.getLoginId().equals(username)){

				return true;
			}
		}

		return false;
	}
}
