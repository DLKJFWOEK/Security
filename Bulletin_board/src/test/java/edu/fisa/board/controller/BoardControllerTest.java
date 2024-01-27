package edu.fisa.board.controller;

//@WebMvcTest(BoardController.class)
public class BoardControllerTest {

//	 	@Autowired
//	    private MockMvc mockMvc;
//
//	    @MockBean
//	    private BoardPostService postService;
//
//	    @Test
//	    @WithMockUser
////	    void showPostList() throws Exception {
//	        BoardPost post1 = new BoardPost();
//	        post1.setId(1L);
//	        post1.setTitle("Test Post 1");
//	        post1.setContent("Content 1");
//	        post1.setCreatedDate(LocalDateTime.now());
//
//	        BoardPost post2 = new BoardPost();
//	        post2.setId(2L);
//	        post2.setTitle("Test Post 2");
//	        post2.setContent("Content 2");
//	        post2.setCreatedDate(LocalDateTime.now());
//
//	        when(postService.getAllPosts()).thenReturn(Arrays.asList(post1, post2));
//
//	        // Act and Assert
//	        mockMvc.perform(get("/board/list"))
//	                .andExpect(status().isOk())
//	                .andExpect(view().name("board/list"))
//	                .andExpect(model().attribute("posts", Arrays.asList(post1, post2)));
//	    }
//
//	    @Test
//	    @WithMockUser
//	    void showWriteForm() throws Exception {
//	        mockMvc.perform(get("/board/write"))
//	                .andExpect(status().isOk())
//	                .andExpect(view().name("board/write"))
//	                .andExpect(model().attributeExists("post"));
//	    }
}
