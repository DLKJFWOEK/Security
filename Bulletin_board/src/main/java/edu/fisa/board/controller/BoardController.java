package edu.fisa.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fisa.board.entity.BoardPost;
import edu.fisa.board.service.BoardPostService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardPostService postService;

    public BoardController(BoardPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list")
    public String showPostList(Model model) {
        List<BoardPost> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "board/list";
    }

    @GetMapping("/write")
    public String showWriteForm(Model model) {
        model.addAttribute("post", new BoardPost());
        return "board/write";
    }

    @PostMapping("/write")
    public String handleWriteForm(BoardPost post) {
        post.setCreatedDate(LocalDateTime.now());
        postService.savePost(post);
        return "redirect:/board/list";
    }
}
