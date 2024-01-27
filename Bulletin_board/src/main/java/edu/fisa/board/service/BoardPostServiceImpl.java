package edu.fisa.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.fisa.board.entity.BoardPost;
import edu.fisa.board.repository.BoardPostRepository;

@Service
public class BoardPostServiceImpl implements BoardPostService{
	
	private final BoardPostRepository postRepository;

    public BoardPostServiceImpl(BoardPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<BoardPost> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public BoardPost getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public void savePost(BoardPost post) {
        postRepository.save(post);
    }
}