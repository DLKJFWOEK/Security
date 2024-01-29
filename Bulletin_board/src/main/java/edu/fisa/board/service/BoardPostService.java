package edu.fisa.board.service;

import java.util.List;


import edu.fisa.board.entity.BoardPost;

public interface BoardPostService {
	
	List<BoardPost> getAllPosts();

    BoardPost getPostById(Long postId);

    void savePost(BoardPost post);
    
    void updatePost(BoardPost post);
    
    void deletePostById(Long postId);
}
