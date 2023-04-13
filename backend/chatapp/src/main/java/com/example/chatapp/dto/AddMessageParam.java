package com.example.chatapp.dto;

import jakarta.validation.constraints.NotBlank;
import java.security.Principal;

import com.example.chatapp.controller.MessageController;

/**
 * DTO
 * {@link MessageController#addMessage(long, AddMessageParam, Principal)}
 */
public class AddMessageParam {
  @NotBlank
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
