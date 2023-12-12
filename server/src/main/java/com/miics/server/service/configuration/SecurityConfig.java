package com.miics.server.service.configuration;

import com.miics.server.dao.mappers.IResultMapper;
import com.miics.server.dao.mappers.ITestMapper;
import com.miics.server.dao.mappers.IUserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
public class SecurityConfig /*extends AbstractSecurityWebApplicationInitializer*/ {

//    private final UserService userService;
//
//    @Autowired
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("http://localhost:8080/users/v1", "http://localhost:8080/swagger-ui/ ").permitAll()
//                        .requestMatchers("http://localhost:8080/**").authenticated())
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//                .build();
//    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userService);
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}