package edu.fisa.lab.finance.model.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fisa.lab.finance.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Optional<Client> findById(String id);
}
