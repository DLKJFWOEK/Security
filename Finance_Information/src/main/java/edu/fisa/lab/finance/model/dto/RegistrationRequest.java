package edu.fisa.lab.finance.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegistrationRequest {

	@NotBlank
	@Size(max = 10)
	private String name;
	
	@NotBlank
	@Size(max = 10)
	private String id;
	
	@NotBlank
	@Size(max = 10)
	private String password;
	
}
