package com.yaloostore.xss.client.persistence;

import com.yaloostore.xss.client.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findByLoginId(String loginId);
}
