package edu.fisa.board.client.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long clientId;
    private String loginId;
    private String pwd;

}
