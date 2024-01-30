package com.yaloostore.xss.client.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

	private String loginId;
	private String pwd;


}
