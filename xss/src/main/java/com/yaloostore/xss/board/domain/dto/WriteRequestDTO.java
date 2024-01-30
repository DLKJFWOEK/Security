package com.yaloostore.xss.board.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteRequestDTO {

    private String title;
    private String content;

}
