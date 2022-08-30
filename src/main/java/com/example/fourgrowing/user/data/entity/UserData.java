package com.example.fourgrowing.user.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private LocalDateTime createTime;

    private LocalDateTime lastLogin;
    
    private String gender;

    @Column(unique = true)
    private String phoneNumber;

    private int age;
}