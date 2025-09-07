package com.example.idp.repository;

import com.example.idp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   User findByEmail(String email);
}
