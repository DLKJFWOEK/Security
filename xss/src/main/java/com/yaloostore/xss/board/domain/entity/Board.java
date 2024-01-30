package com.yaloostore.xss.board.domain.entity;

import java.time.LocalDate;

import com.yaloostore.xss.board.domain.dto.BoardDTO;
import com.yaloostore.xss.board.domain.dto.WriteRequestDTO;
import com.yaloostore.xss.client.domain.entity.Client;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boards")
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private String title;

    private String content;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdDate;


    @LastModifiedDate
    @Column(name = "updated_at", updatable = false)
    private LocalDate updatedDate;


    public static Board createBoardPost(BoardDTO boardDTO, Client client){
        return Board.builder()
                .client(client)
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();

    }
    public static Board createBoardPost(WriteRequestDTO dto, Client client){
        return Board.builder()
                .client(client)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

    }
}