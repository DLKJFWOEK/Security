package com.yaloostore.xss.board.controller;


import com.yaloostore.xss.board.domain.dto.BoardDTO;
import com.yaloostore.xss.board.domain.entity.Board;
import com.yaloostore.xss.board.persistence.BoardRepository;
import com.yaloostore.xss.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/xss/board/cookie")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService service;

    @GetMapping("/lists")
    public ResponseEntity<List<Board>> getAllPosts() {
        List<Board> posts = service.getAllPosts();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Board> getPostById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(service.getBoardById(id));
    }

    @PostMapping("/write")
    public ResponseEntity<Board> writePost(@RequestBody BoardDTO dto) {

        return ResponseEntity.ok(service.savePost(dto));
    }



}
