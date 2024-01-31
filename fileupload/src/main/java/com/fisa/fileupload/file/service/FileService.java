package com.fisa.fileupload.file.service;

import java.util.Optional;

import com.fisa.fileupload.file.domain.File;

public interface FileService {
	Optional<File> save(File file);

	Optional<File> findByBoardId(Long boardId);


}
