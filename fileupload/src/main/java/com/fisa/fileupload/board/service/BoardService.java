package com.fisa.fileupload.board.service;

import java.util.List;
import java.util.Optional;

import com.fisa.fileupload.board.domain.dto.BoardDTO;
import com.fisa.fileupload.board.domain.dto.UpdateBoardDTO;
import com.fisa.fileupload.board.domain.dto.WriteRequestDTO;
import com.fisa.fileupload.board.domain.entity.BoardPost;

public interface BoardService {

    List<BoardPost> getAllPosts();

    BoardPost getBoardById(Long postId);

    BoardPost savePost(BoardDTO boardDTO);

    BoardDTO writePost(WriteRequestDTO dto, String loginId);

    void updatePost(UpdateBoardDTO dto);

    void deletePostById(Long postId);

    Optional<BoardPost> findById(Long id);


}
