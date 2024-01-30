package com.yaloostore.xss.board.service.impl;

import com.yaloostore.xss.board.domain.dto.BoardDTO;
import com.yaloostore.xss.board.domain.dto.UpdateBoardDTO;
import com.yaloostore.xss.board.domain.dto.WriteRequestDTO;
import com.yaloostore.xss.board.domain.entity.Board;
import com.yaloostore.xss.board.persistence.BoardRepository;
import com.yaloostore.xss.board.service.BoardService;
import com.yaloostore.xss.client.domain.entity.Client;
import com.yaloostore.xss.client.persistence.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ClientRepository clientRepository;


    @Override
    public List<Board> getAllPosts() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.getById(id);
    }

    @Override
    public Board savePost(BoardDTO dto) {

        Optional<Client> op = clientRepository.findByLoginId(dto.getLoginId());
        Client client = null;
        if (op.isPresent()){
            client = op.get();

            Board boardPost = Board.createBoardPost(dto, client);
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

            Board boardPost = Board.createBoardPost(dto, client);
            boardRepository.save(boardPost);

            return BoardDTO.fromEntity(boardPost);
        }

        return null;
    }


    @Override
    public void updatePost(UpdateBoardDTO dto) {
        Optional<Board> op = boardRepository.findById(dto.getId());
        if(op.isPresent()){

            Board board = op.get();
            board.setTitle(dto.getTitle());
            board.setContent(dto.getContent());
            //jpa 더디체킹으로 save() 안 해도 됨
        }

    }

    @Override
    public void deletePostById(Long id) {
        boardRepository.deleteById(id);

    }
}
