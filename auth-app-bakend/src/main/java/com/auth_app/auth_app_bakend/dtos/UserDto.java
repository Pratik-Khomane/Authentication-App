package com.auth_app.auth_app_bakend.dtos;

import com.auth_app.auth_app_bakend.entities.Provider;
import com.auth_app.auth_app_bakend.entities.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {

    private UUID id;
    private String email;
    private String name;
    private String password;
    private String image;
    private boolean enabled;
    private Instant created_at = Instant.now();
    private Instant updated_at = Instant.now();
    private Provider provider = Provider.LOCAL;
    private Set<RoleDto> roles= new HashSet<>();
}
