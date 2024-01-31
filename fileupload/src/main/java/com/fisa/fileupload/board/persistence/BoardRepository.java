package com.fisa.fileupload.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.fileupload.board.domain.entity.BoardPost;



public interface BoardRepository extends JpaRepository<BoardPost, Long>{

}
