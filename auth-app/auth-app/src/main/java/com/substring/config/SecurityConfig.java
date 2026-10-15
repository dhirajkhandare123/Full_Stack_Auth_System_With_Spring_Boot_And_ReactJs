package com.substring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
class SecurityConfig{
    @Bean
    public UserDetailsService users(){
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails user1 = userBuilder.username("dhiraj").password("pass@1234").roles("ADMIN").build();
        UserDetails user2 = userBuilder.username("vikas").password("abc").roles("USER").build();
        UserDetails user3 = userBuilder.username("ram").password("xyz").roles("USER").build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}