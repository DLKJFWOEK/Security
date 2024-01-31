package com.fisa.fileupload.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fisa.fileupload.board.domain.dto.WriteRequestDTO;
import com.fisa.fileupload.board.domain.entity.BoardPost;
import com.fisa.fileupload.board.service.BoardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/xss/board/cookie")
@RequiredArgsConstructor
@Slf4j
public class BoardRestController {



    private final BoardService service;


    @GetMapping("/lists")
    public ResponseEntity<List<BoardPost>> getAllPosts() {
        List<BoardPost> posts = service.getAllPosts();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardPost> getPostById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(service.getBoardById(id));
    }

    @PostMapping("/write")
    public void writePost(@RequestBody WriteRequestDTO writeRequestDTO,
        HttpServletRequest request) throws IOException {


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
