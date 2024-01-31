package com.fisa.fileupload.board.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBoardDTO {

    private Long id;

    private String loginId;

    private String title;

    private String content;

}
