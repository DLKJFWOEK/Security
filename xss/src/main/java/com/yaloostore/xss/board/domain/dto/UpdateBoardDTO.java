package com.yaloostore.xss.board.domain.dto;



import lombok.*;

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
