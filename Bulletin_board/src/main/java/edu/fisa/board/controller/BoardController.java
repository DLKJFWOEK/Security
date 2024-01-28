package edu.fisa.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/detail/{postId}")
    public String showPostDetail(@PathVariable("postId") Long postId, Model model) {
        BoardPost post = postService.getPostById(postId);
        if (post != null) {
            model.addAttribute("post", post);
            return "board/detail";
        } else {
            return "redirect:/board/list";
        }
    }

    @GetMapping("/write")
    public String showWriteForm(Model model) {
        model.addAttribute("post", new BoardPost());
        return "board/write";
    }

    @PostMapping("/write")
    public String handleWriteForm(BoardPost post) {
    	LocalDateTime now = LocalDateTime.now();
    	post.setCreatedDate(now.toLocalDate());
        postService.savePost(post);
        return "redirect:/board/list";
    }
    
    @GetMapping("/edit/{postId}")
    public String showEditForm(@PathVariable("postId") Long postId, Model model) {
        BoardPost post = postService.getPostById(postId);
        if (post != null) {
            model.addAttribute("post", post);
            return "board/edit";
        } else {
            return "redirect:/board/list";
        }
    }

    @PostMapping("/edit")
    public String handleEditForm(@ModelAttribute("post") BoardPost post) {
        postService.updatePost(post);
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{postId}")
    public String showDeleteForm(@PathVariable("postId") Long postId, Model model) {
        BoardPost post = postService.getPostById(postId);
        if (post != null) {
            model.addAttribute("post", post);
            return "board/delete";
        } else {
            return "redirect:/board/list";
        }
    }

    @PostMapping("/delete/{postId}")
    public String handleDeleteForm(@PathVariable("postId") Long postId, Model model) {
    	if (postId != null) {
            postService.deletePostById(postId);
        }
        List<BoardPost> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        
        return "redirect:/board/list"; // 삭제 후에 "board/list" 페이지로 redirect
    }
}
