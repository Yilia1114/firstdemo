package com.example.firstdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailManager(BCryptPasswordEncoder encoder) {
//        UserDetails user1 = User
//                .withUsername("user1")
//                .password(encoder.encode("111"))
//                .authorities("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(List.of(user1));
//    }

}




