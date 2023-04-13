package com.example.chatapp.dto;

import com.example.chatapp.controller.RegisterController;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO {@link RegisterController#register(RegisterParam)}
 */
public class RegisterParam {
  @NotBlank
  private String username;

  @NotBlank
  private String password;

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
