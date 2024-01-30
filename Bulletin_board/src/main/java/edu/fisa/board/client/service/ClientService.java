package edu.fisa.board.client.service;

import edu.fisa.board.client.domain.entity.Client;
import edu.fisa.board.client.exception.NotFoundClientException;
import edu.fisa.board.client.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Client isAuthentictaionClient(String loginId){

        Optional<Client> op = clientRepository.findByLoginId(loginId);

        if (op.isPresent()){

            return op.get();
        }
       throw new NotFoundClientException("회원 아이디 비밀번호를 다시 입력하세요");


    }


}
