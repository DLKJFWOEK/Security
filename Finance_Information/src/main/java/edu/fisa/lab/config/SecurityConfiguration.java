package edu.fisa.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;

import edu.fisa.lab.finance.security.CustomAccessDeniedHandler;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler(customAccessDeniedHandler));
        return http.build();	
	}
	
	//accessDeniedHandler Bean 을 직접 생성하는 부분
//	 @Bean
//	 public AccessDeniedHandler accessDeniedHandler() {
//	 return new CustomAccessDeniedHandler(); // 사용자 정의 AccessDeniedHandler
//	 }
}
