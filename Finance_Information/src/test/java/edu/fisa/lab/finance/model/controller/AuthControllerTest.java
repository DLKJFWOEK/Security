package edu.fisa.lab.finance.model.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.fisa.lab.finance.model.TestConfig;
import edu.fisa.lab.finance.model.dto.RegistrationRequest;
import edu.fisa.lab.finance.model.service.ClientService;


@ContextConfiguration(classes = TestConfig.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private ClientService clientService;
	
	@InjectMocks
	private AuthController authController;
	
	@Test
	@WithMockUser
	public void testRegisterClient() throws Exception{
		// Arrange
		RegistrationRequest request = new RegistrationRequest();
		request.setName("이강진");
		request.setId("frog0404");
		request.setPassword("securepassword");
		
		//Mocking the behavior of the clientService.registerClient method
		doNothing().when(clientService).registerClient(any(), any(), any());
		
		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
				.with(csrf())
				.content(asJsonString(request).getBytes())
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("회원가입이 완료되었습니다."));
	}
	
	//Utility method to convert an object to JSON string
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}