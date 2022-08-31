package com.example.fourgrowing.user.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateAdminDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}

