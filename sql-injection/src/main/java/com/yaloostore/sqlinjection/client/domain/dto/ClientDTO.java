package com.yaloostore.sqlinjection.client.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private Long clientId;
    private String loginId;
    private String pwd;

}
