package com.fisa.fileupload.file.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.fileupload.file.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {
	Optional<File> findByBoardPostId(Long boardPostId);

}
