package com.substring.mapper;

import com.substring.dtos.RoleDTO;
import com.substring.dtos.UserDTO;
import com.substring.entity.Roles;
import com.substring.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private UserMapper() {
        // Utility class
    }

    public UserDTO toDTO(User user) {

        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setImage(user.getImage());
        dto.setEnable(user.getEnable());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setProvider(user.getProvider());

        if (user.getRoles() != null) {
            dto.setRoles(
                    user.getRoles()
                            .stream()
                            .map(UserMapper::toRoleDTO)
                            .collect(Collectors.toSet())
            );
        } else {
            dto.setRoles(new HashSet<>());
        }

        return dto;
    }

    public User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setImage(dto.getImage());
        user.setPassword(dto.getPassword());
        user.setEnable(dto.getEnable());
        user.setProvider(dto.getProvider());

        return user;
    }

    private static RoleDTO toRoleDTO(Roles role) {

        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());

        return roleDTO;
    }
}