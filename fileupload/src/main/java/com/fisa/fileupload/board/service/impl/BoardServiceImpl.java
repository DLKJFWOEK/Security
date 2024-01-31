package com.fisa.fileupload.board.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fisa.fileupload.board.domain.dto.BoardDTO;
import com.fisa.fileupload.file.domain.FileRequestDTO;
import com.fisa.fileupload.board.domain.dto.UpdateBoardDTO;
import com.fisa.fileupload.board.domain.dto.WriteRequestDTO;
import com.fisa.fileupload.board.domain.entity.BoardPost;
import com.fisa.fileupload.board.persistence.BoardRepository;
import com.fisa.fileupload.board.service.BoardService;
import com.fisa.fileupload.client.domain.entity.Client;
import com.fisa.fileupload.client.persistence.ClientRepository;
import com.fisa.fileupload.file.repository.FileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ClientRepository clientRepository;
    private final FileRepository fileRepository;


    @Override
    public List<BoardPost> getAllPosts() {
        return boardRepository.findAll();
    }

    @Override
    public BoardPost getBoardById(Long id) {
        return boardRepository.getById(id);
    }

    @Override
    public BoardPost savePost(BoardDTO dto) {

        Optional<Client> op = clientRepository.findByLoginId(dto.getLoginId());
        Client client = null;
        if (op.isPresent()){
            client = op.get();

            BoardPost boardPost = BoardPost.createBoardPost(dto, client);
            boardRepository.save(boardPost);

            return boardPost;
        }

        throw new RuntimeException();


    }

    @Override
    public BoardDTO writePost(WriteRequestDTO dto, String loginId) {
        Optional<Client> op = clientRepository.findByLoginId(loginId);
        log.info(op.get().getLoginId());

        Client client = null;


        if (op.isPresent()){
            client = op.get();

            BoardPost boardPost = BoardPost.createBoardPost(dto, client);
            boardRepository.save(boardPost);

            return BoardDTO.fromEntity(boardPost);
        }

        return null;
    }




    @Override
    public void updatePost(UpdateBoardDTO dto) {
        Optional<BoardPost> op = boardRepository.findById(dto.getId());
        if(op.isPresent()){

            BoardPost board = op.get();
            board.setTitle(dto.getTitle());
            board.setContent(dto.getContent());
            //jpa 더디체킹으로 save() 안 해도 됨
        }

    }

    @Override
    public void deletePostById(Long id) {
        boardRepository.deleteById(id);

    }

    @Override
    public Optional<BoardPost> findById(Long id) {
        return boardRepository.findById(id);
    }
}
