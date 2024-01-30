package com.yaloostore.xss.board.service;


import com.yaloostore.xss.board.domain.dto.BoardDTO;
import com.yaloostore.xss.board.domain.dto.UpdateBoardDTO;
import com.yaloostore.xss.board.domain.dto.WriteRequestDTO;
import com.yaloostore.xss.board.domain.entity.Board;
import com.yaloostore.xss.board.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {

    List<Board> getAllPosts();

    Board getBoardById(Long postId);

    Board savePost(BoardDTO boardDTO);

    BoardDTO writePost(WriteRequestDTO dto, String loginId);

    void updatePost(UpdateBoardDTO dto);

    void deletePostById(Long postId);


}
