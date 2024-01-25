package edu.fisa.lab.finance.client.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.finance.client.domain.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Optional<Client> findById(String id);

	Optional<Client> findByLoginId(String loginId);
}
