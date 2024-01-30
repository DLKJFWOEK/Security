package com.yaloostore.sqlinjection.client.servicr;


import com.yaloostore.sqlinjection.client.domain.dto.ClientDTO;
import com.yaloostore.sqlinjection.client.persistence.ClientDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientDAO dao;


    public boolean isAuthenticationClient_pre(String loginId,String pwd){
        ClientDTO dto = dao.findClientByLoginId(loginId, pwd);

        return dto.getLoginId().equals(loginId) && dto.getPwd().equals(pwd);
    }



}
