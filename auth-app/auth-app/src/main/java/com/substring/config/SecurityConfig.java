//package com.substring.config;
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
