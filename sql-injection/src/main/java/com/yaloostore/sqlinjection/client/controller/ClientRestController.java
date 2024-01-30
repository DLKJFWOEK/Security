package com.yaloostore.sqlinjection.client.controller;


import com.yaloostore.sqlinjection.client.domain.dto.ClientDTO;
import com.yaloostore.sqlinjection.client.persistence.ClientDAO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sql-injection")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientDAO dao;


    /*
    * 해당 작업은 sql injection이 통하는 허접한 보안을 하고 있어요 ~
    * */
    @PostMapping("/login")
    public ResponseEntity<ClientDTO> login(@RequestBody ClientDTO clientDTO){

        ClientDTO response = dao.findClientByLoginId(clientDTO.getLoginId(), clientDTO.getPwd());

        return ResponseEntity.ok(response);

    }

    @PostMapping("/pre-login")
    public ResponseEntity<ClientDTO> login_pre(@RequestBody ClientDTO clientDTO){

        ClientDTO response = dao.findClientByLoginId_pre(clientDTO.getLoginId(), clientDTO.getPwd());

        return ResponseEntity.ok(response);

    }



}
