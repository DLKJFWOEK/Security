package com.yaloostore.xss.client.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long clientId;
    private String loginId;
    private String pwd;

}
