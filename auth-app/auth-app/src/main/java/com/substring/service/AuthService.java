package com.substring.service;

import com.substring.dtos.UserDTO;
import com.substring.entity.User;
import org.springframework.stereotype.Service;


public interface AuthService {
    UserDTO registerUser(UserDTO userDTO);
}