package com.fisa.fileupload.board.domain.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fisa.fileupload.board.domain.dto.BoardDTO;
import com.fisa.fileupload.board.domain.dto.WriteRequestDTO;
import com.fisa.fileupload.client.domain.entity.Client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boardPost")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Builder
public class BoardPost {

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
	public static BoardPost createBoardPost(BoardDTO boardDTO, Client client){
		return BoardPost.builder()
			.client(client)
			.title(boardDTO.getTitle())
			.content(boardDTO.getContent())
			.build();

	}
	public static BoardPost createBoardPost(WriteRequestDTO dto, Client client){
		return BoardPost.builder()
			.client(client)
			.title(dto.getTitle())
			.content(dto.getContent())
			.build();

	}

	public static BoardPost fromDto(){

		return BoardPost.builder().build();
	}

}