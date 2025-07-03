package com.sportyshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailContainingIgnoreCase(String email);
}
