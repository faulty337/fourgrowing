package com.example.fourgrowing.user;


import com.example.fourgrowing.user.data.dto.UserCreateDto;
import com.example.fourgrowing.user.data.entity.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserData create(UserCreateDto userCreateDto) {
        UserData user = new UserData();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setCreateTime(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setGender(userCreateDto.getGender());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setAge(userCreateDto.getAge());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword1()));
        this.userRepository.save(user);
        return user;
    }
}
