package edu.fisa.board.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

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
        
        return "redirect:/board/list"; // 삭제 후에 "board/list" 페이지로 redirect
    }
    
    @GetMapping("/uploadfile") // Handler for showing upload file form
    public String showUploadForm() {
        return "board/uploadfile"; // Renders uploadfile.html
    }
    
    @PostMapping("/upload") // Handler for file upload
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    	
    	if (!file.isEmpty()) {
            try {
            	
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDir + file.getOriginalFilename());
                Files.write(path, bytes);
                
                // Add file information to model for display in upload.html
                model.addAttribute("fileName", file.getOriginalFilename());
                model.addAttribute("fileType", file.getContentType());
                model.addAttribute("fileSize", file.getSize());
                model.addAttribute("tempFileName", file.getOriginalFilename());
                
                // You can also add other attributes or perform additional logic here
                System.out.println("File information added to model successfully.");
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/board/upload";
    }
    
    @GetMapping("/upload") // Handler for showing upload status
    public String showUploadStatus(Model model) {
    	return "board/upload"; // Renders upload.html
    }
}
