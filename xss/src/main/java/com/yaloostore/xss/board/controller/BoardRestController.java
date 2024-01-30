package com.yaloostore.xss.board.controller;


import com.yaloostore.xss.board.domain.dto.BoardDTO;
import com.yaloostore.xss.board.domain.dto.WriteRequestDTO;
import com.yaloostore.xss.board.domain.entity.Board;
import com.yaloostore.xss.board.persistence.BoardRepository;
import com.yaloostore.xss.board.service.BoardService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/xss/board/cookie")
@RequiredArgsConstructor
@Slf4j
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
    public void writePost(@RequestBody WriteRequestDTO writeRequestDTO, HttpServletRequest request) {


        String loginId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("loginId")){
                    loginId = cookie.getValue();
                }
            }
        }

        log.info(loginId);
        log.info(writeRequestDTO.getTitle());
        log.info(writeRequestDTO.getContent());

        service.writePost(writeRequestDTO, loginId);



    }


}
