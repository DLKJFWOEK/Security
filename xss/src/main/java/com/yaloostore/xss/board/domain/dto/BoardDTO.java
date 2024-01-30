package com.yaloostore.xss.board.domain.dto;

import com.yaloostore.xss.board.domain.entity.Board;
import com.yaloostore.xss.client.domain.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {


    private Long id;

    private String loginId;

    private String title;

    private String content;

    private String createdDate;

    private String updatedDate;

    public static BoardDTO fromEntity(Board board){

        return BoardDTO.builder()
                .id(board.getId())
                .loginId(board.getClient().getLoginId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(String.valueOf(board.getCreatedDate()))
                .updatedDate(String.valueOf(board.getUpdatedDate()))
                .build();

    }


}
