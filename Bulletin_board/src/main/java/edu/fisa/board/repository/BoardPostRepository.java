package edu.fisa.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.fisa.board.entity.BoardPost;

public interface BoardPostRepository extends JpaRepository<BoardPost, Long>{
	// 추가적인 쿼리 메소드가 필요한 경우 작성
}
