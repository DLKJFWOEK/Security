package com.yaloostore.xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class XssApplication {

	public static void main(String[] args) {
		SpringApplication.run(XssApplication.class, args);
	}

}
