package edu.fisa.lab.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.access.AccessDeniedHandler;

import edu.fisa.lab.finance.security.filter.JsonAuthenticationFilter;
import edu.fisa.lab.finance.security.filter.JwtOncePerRequestFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http.formLogin(AbstractHttpConfigurer::disable);

		http.addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtOncePerRequestFilter(),UsernamePasswordAuthenticationFilter.class)

		;

        return http.build();	
	}

	@Bean
	public JsonAuthenticationFilter jsonAuthenticationFilter() {

		JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();

		jsonAuthenticationFilter.setAuthenticationManager(new ProviderManager());
		return jsonAuthenticationFilter;

	}

	@Bean
	public JwtOncePerRequestFilter jwtOncePerRequestFilter() {
		return new JwtOncePerRequestFilter();

	}

	@Bean
	public PasswordEncoder passwordEncoder(){

		return new BCryptPasswordEncoder();

	}
}
