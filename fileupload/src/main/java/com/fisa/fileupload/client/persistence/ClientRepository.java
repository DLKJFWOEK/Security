package com.fisa.fileupload.client.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.fileupload.client.domain.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findByLoginId(String loginId);
}
