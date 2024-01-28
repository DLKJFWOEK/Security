package edu.fisa.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fisa.board.entity.BoardPost;

public interface BoardPostRepository extends JpaRepository<BoardPost, Long>{
	
}
