package com.example.chatapp.controller;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO {@link RegisterController#register(RegisterParam)}
 */
public class RegisterParam {
  @NotBlank
  String username;

  @NotBlank
  String password;

  String getUsername() {
    return this.username;
  }

  String getPassword() {
    return this.password;
  }

  void setUsername(String username) {
    this.username = username;
  }

  void setPassword(String password) {
    this.password = password;
  }
}
