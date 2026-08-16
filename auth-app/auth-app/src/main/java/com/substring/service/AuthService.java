package com.substring.service;

import com.substring.dtos.UserDTO;

public interface AuthService {
    UserDTO registerUser(UserDTO userDTO);
}
