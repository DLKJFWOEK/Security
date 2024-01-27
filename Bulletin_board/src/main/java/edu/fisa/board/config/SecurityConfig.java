//package edu.fisa.board.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authorize ->
//                authorize
//                    .requestMatchers("/login","/board/list").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .formLogin(formLogin ->
//                formLogin
//                    .loginPage("/login") // 로그인 페이지 경로
//                    .defaultSuccessUrl("/board/list", true) // 로그인 성공 후의 경로
//                    .permitAll()
//            )
//            .logout(logout ->
//                logout
//                    .permitAll()
//            )
//            .sessionManagement(sessionManagement ->
//            	sessionManagement
//            		.maximumSessions(1) // 동시 로그인을 1개로 제한하거나 필요에 따라 조절
//            		.sessionRegistry(sessionRegistry()) // 세션 레지스트리를 설정
//            		.expiredUrl("/login?expired") // 세션이 만료되면 로그인 페이지로 리디렉션
//            );
//        return http.build();
//    }
//    @Bean
//    public SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//}