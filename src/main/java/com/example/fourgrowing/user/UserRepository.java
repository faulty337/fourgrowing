package com.example.fourgrowing.user;

import com.example.fourgrowing.user.data.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByusername(String username);
}
