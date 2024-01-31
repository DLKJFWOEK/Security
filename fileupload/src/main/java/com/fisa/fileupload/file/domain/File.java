package com.fisa.fileupload.file.domain;

import com.fisa.fileupload.board.domain.entity.BoardPost;


import jakarta.persistence.Entity;
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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
@Getter
@Builder
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String fileName;

	private String contentType;

	private Long fileSize;

	private String fileSavePath;

	private String originFileName;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private BoardPost boardPost;


	public static File createFile(FileRequestDTO dto, BoardPost boardPost){

		return File.builder()
			.fileName(dto.getFileName())
			.contentType(dto.getContentType())
			.fileSize(dto.getFileSize())
			.fileSavePath(dto.getFileSavePath())
			.originFileName(dto.getOriginFileName())
			.boardPost(boardPost)
			.build();

	}
}
