package com.example.chatapp.controller;

import jakarta.validation.constraints.NotBlank;
import java.security.Principal;

/**
 * DTO
 * {@link MessageController#addMessage(long, AddMessageParam, Principal)}
 */
public class AddMessageParam {
  @NotBlank
  String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
