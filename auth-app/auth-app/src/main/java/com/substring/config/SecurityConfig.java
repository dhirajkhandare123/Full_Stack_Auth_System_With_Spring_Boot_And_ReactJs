package com.substring.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsService users(){
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        UserDetails user1=userBuilder.username("dhiraj1@gmail.com").password("1234").roles("ADMIN").build();
//
//        UserDetails user2=userBuilder.username("dhiraj2@gmail.com").password("1234").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
//}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(e -> e.disable());
        http.authorizeHttpRequests(authorizeHttpRequest ->
                authorizeHttpRequest.requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/login").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
