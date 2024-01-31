package com.fisa.fileupload.file.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileRequestDTO {


	private String fileName;
	private String contentType;
	private Long fileSize;
	private String fileSavePath;
	private String OriginFileName;
}
