package com.yaloostore.xss.board.controller;


import com.yaloostore.xss.board.domain.dto.BoardDTO;
import com.yaloostore.xss.board.domain.dto.WriteRequestDTO;
import com.yaloostore.xss.board.domain.entity.Board;
import com.yaloostore.xss.board.service.BoardService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {


    private final BoardService boardService;


    @GetMapping("/write")
    public String writeForm(Model model
                            ,HttpServletRequest request,
                            HttpServletResponse response){

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());

        model.addAttribute("writeRequestDTO", new WriteRequestDTO());

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "login";
        }


        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginId")){
                model.addAttribute("loginId", cookie.getValue());
            }
        }


        return "board/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute("writeRequestDTO") WriteRequestDTO writeRequestDTO,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response){

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        model.addAttribute("writeRequestDTO", writeRequestDTO);


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

        BoardDTO boardDTO = boardService.writePost(writeRequestDTO, loginId);

        log.info(boardDTO.getContent());

        return "redirect:/boards/lists";
    }


    @GetMapping("/lists")
    public String boardList(Model model, HttpServletRequest request, HttpServletResponse response){

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());

        List<Board> allPosts = boardService.getAllPosts();

        List<BoardDTO> posts= new ArrayList<>();

        for (Board board : allPosts) {
            posts.add(BoardDTO.fromEntity(board));
        }

        model.addAttribute("posts", posts);


        return "board/list";

    }

    @GetMapping("/detail/{id}")
    public String boardDetail(@PathVariable Long id,
                              Model model,
                              HttpServletResponse response,
                              HttpServletRequest request) {


        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());


        Board board = boardService.getBoardById(id);

        model.addAttribute("post", board);

        return "board/detail";
    }

}
