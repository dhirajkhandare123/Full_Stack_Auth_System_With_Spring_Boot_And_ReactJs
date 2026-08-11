package com.substring.dtos;

import com.substring.entity.Provider;
import com.substring.entity.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    private UUID id;

    private String email;
    private String name;
    private String image;
    private Boolean enable=true;
    private Instant createdAt=Instant.now();
    private Instant updatedAt=Instant.now();

    private Set<RoleDTO> roles = new HashSet<>();
}
