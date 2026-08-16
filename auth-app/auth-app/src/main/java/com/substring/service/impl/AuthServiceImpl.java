package com.substring.service.impl;

import com.substring.dtos.UserDTO;
import com.substring.service.AuthService;
import com.substring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    @Override
    public UserDTO registerUser(UserDTO userDTO) {

        UserDTO userDTO1 = userService.createUser(userDTO);
        return userDTO1;
    }
}
