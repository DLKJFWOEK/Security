package com.fisa.fileupload.file.service;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import com.fisa.fileupload.file.domain.File;
import com.fisa.fileupload.file.repository.FileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

	private final FileRepository repository;

	@Override
	public Optional<File> save(File file) {

		return Optional.ofNullable(repository.save(file));

	}

	@Override
	public Optional<File> findByBoardId(Long boardId) {
		return repository.findByBoardPostId(boardId);
	}

}
