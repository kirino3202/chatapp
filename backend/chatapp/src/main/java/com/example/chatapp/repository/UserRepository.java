package com.example.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chatapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT id, username, enabled FROM user WHERE username = ?1", nativeQuery = true)
  public User findByUsername(String username);

}
