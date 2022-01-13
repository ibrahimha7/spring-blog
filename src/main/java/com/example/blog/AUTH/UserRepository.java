package com.example.blog.AUTH;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findUserById (Long id);
    AppUser findUserByPhoneNumber(String phoneNumber);
    AppUser findUserByName(String name);
}
