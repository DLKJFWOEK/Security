package edu.fisa.lab.finance.security.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.fisa.lab.finance.client.domain.entity.Client;
import edu.fisa.lab.finance.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final ClientRepository clientRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Client> op = clientRepository.findByLoginId(username);
		if (op.isEmpty()){
			throw new BadCredentialsException("해당 정보를 가진 회원이 존재하지 않습니다.");
		}

		Client client = op.get();
		return new CustomUserDetails(client);

	}


}
