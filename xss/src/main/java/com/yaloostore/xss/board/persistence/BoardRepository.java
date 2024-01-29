package com.yaloostore.xss.board.persistence;
import com.yaloostore.xss.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BoardRepository extends JpaRepository<Board, Long>{

}
