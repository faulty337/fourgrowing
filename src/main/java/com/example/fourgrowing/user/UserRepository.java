package com.example.fourgrowing.user;

import com.example.fourgrowing.user.data.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Long> {
}
