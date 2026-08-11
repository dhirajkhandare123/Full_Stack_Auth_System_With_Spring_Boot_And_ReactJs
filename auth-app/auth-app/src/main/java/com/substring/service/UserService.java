package com.substring.service;

import com.substring.dtos.UserDTO;
import com.substring.entity.User;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO updateUser(UserDTO userDTO, String userid);

    void deleteUser(String userId);

    UserDTO getUserById(String userId);

    Iterable<UserDTO> getAllUsers();
}
