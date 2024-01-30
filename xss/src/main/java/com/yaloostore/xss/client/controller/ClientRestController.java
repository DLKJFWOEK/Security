package com.yaloostore.xss.client.controller;


import com.yaloostore.xss.client.domain.dto.ClientDTO;
import com.yaloostore.xss.client.domain.entity.Client;
import com.yaloostore.xss.client.service.ClientService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xss/client")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService service;

    @PostMapping("/cookie/login")
    public ResponseEntity<String> login(@RequestBody ClientDTO clientDTO,
                                        HttpServletResponse response){

        Client client = service.isAuthentictaionClient(clientDTO.getLoginId());

        settingCookie(response, clientDTO, client);


        return ResponseEntity.ok("success login!");

    }

    private static void settingCookie(HttpServletResponse response, ClientDTO clientDTO, Client client) {
        Cookie loginId = new Cookie("loginId", clientDTO.getLoginId());
        Cookie pwd = new Cookie("pwd", client.getPwd());
        Cookie clientId = new Cookie("clientId", clientDTO.getLoginId());

        loginId.setMaxAge(900000);
        pwd.setMaxAge(900000);
        clientId.setMaxAge(900000);

        response.addCookie(loginId);
        response.addCookie(pwd);
        response.addCookie(clientId);
    }


}
