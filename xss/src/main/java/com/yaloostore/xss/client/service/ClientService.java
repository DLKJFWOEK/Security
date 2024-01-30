package com.yaloostore.xss.client.service;

import com.yaloostore.xss.client.domain.dto.ClientDTO;
import com.yaloostore.xss.client.domain.dto.LoginRequestDto;
import com.yaloostore.xss.client.domain.entity.Client;
import com.yaloostore.xss.client.exception.NotFoundClientException;
import com.yaloostore.xss.client.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;


    public Client isAuthentictaionClient(String loginId){

        Optional<Client> op = clientRepository.findByLoginId(loginId);

        if (op.isPresent()){

            return op.get();
        }
       throw new NotFoundClientException("회원 아이디 비밀번호를 다시 입력하세요");


    }

    public ClientDTO isAuthentication(LoginRequestDto loginRequestDto){

        Optional<Client> op = clientRepository.findByLoginId(loginRequestDto.getLoginId());
        log.info(op.get().getLoginId());
        Client client =null;

        if (op.isPresent()){
            client = op.get();

            if (client.getPwd().equals(loginRequestDto.getPwd()) && client.getLoginId().equals(loginRequestDto.getLoginId())){

                return new ClientDTO(client.getClientId(), client.getLoginId(), client.getPwd());

            }
        }
        return null;
    }


}
