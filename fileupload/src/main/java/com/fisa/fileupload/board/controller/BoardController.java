package com.fisa.fileupload.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fisa.fileupload.board.domain.dto.BoardDTO;
import com.fisa.fileupload.board.domain.dto.WriteRequestDTO;
import com.fisa.fileupload.board.domain.entity.BoardPost;
import com.fisa.fileupload.board.service.BoardService;
import com.fisa.fileupload.file.domain.File;
import com.fisa.fileupload.file.service.FileService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {


    private final BoardService boardService;

    private final FileService fileService;


    @Value("${file.upload-dir}")
    private String FILE_PATH;

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
                        HttpServletResponse response,
        @RequestParam(value = "files", required = false) MultipartFile files) throws IOException {

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
        model.addAttribute("loginId", loginId);


        BoardDTO boardDTO = boardService.writePost(writeRequestDTO, loginId);
        if (!files.isEmpty()){
            byte[] filesBytes = files.getBytes();
            String encode = URLEncoder.encode(files.getOriginalFilename(), StandardCharsets.UTF_8);
            Path path = Paths.get(FILE_PATH, encode);
            log.info("File path: " + path.toString());
            BoardPost boardPost = boardService.getBoardById(boardDTO.getId());

            Files.write(path, filesBytes);

            File file = File.builder()
                .fileName(files.getName())
                .contentType(files.getContentType())
                .fileSize(files.getSize())
                .fileSavePath(FILE_PATH)
                .originFileName(files.getOriginalFilename())
                .boardPost(boardPost)
                .build();

            fileService.save(file);
        }

        log.info(boardDTO.getContent());

        return "redirect:/boards/lists";
    }


    @GetMapping("/lists")
    public String boardList(Model model, HttpServletRequest request, HttpServletResponse response){

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());

        List<BoardPost> allPosts = boardService.getAllPosts();

        List<BoardDTO> posts= new ArrayList<>();

        for (BoardPost board : allPosts) {
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




        BoardPost board = boardService.getBoardById(id);

        Optional<File> op = fileService.findByBoardId(board.getId());

        model.addAttribute("post", board);

        log.info("dududud 여여여여여기가 나옿냐?{}",op.toString());
        if (op.isPresent()){
            model.addAttribute("file", op.get());
        }

        return "board/detail";
    }

}
