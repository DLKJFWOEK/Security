package com.fisa.fileupload.board.domain.dto;

import com.fisa.fileupload.board.domain.entity.BoardPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static BoardDTO fromEntity(BoardPost board){

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
