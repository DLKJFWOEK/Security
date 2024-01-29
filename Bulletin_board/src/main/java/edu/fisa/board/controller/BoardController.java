package edu.fisa.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import edu.fisa.board.entity.BoardPost;
import edu.fisa.board.service.BoardPostService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private final BoardPostService postService;
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
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
        
        return "redirect:/board/list"; 
    }
    
    @GetMapping("/uploadfile") 
    public String showUploadForm() {
        return "board/uploadfile"; 
    }
    
    @PostMapping("/upload") 
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    	
    	if (!file.isEmpty()) {
            try {
            	
                byte[] bytes = file.getBytes();
                
             
                String encodedFileName = URLEncoder.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
                Path path = Paths.get(uploadDir, encodedFileName);
                Files.write(path, bytes);
                
                logger.debug("Uploaded file name: {}", encodedFileName);
                logger.debug("File size: {} bytes", file.getSize());
                logger.debug("File type: {}", file.getContentType());
                logger.debug("Uploaded temporary file name: {}", encodedFileName);

                
                model.addAttribute("fileName", encodedFileName);
                model.addAttribute("fileType", file.getContentType());
                model.addAttribute("fileSize", file.getSize());
                model.addAttribute("tempFileName", encodedFileName);
                
                

            } catch (IOException e) {
            	logger.error("Error occurred during file upload", e);
                e.printStackTrace();
            }
        }
        return "/board/upload";
    }
    
    @GetMapping("/upload") 
    public String showUploadStatus(Model model) {
    	return "board/upload"; 
    }
}
