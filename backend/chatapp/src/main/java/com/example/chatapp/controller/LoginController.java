package com.example.chatapp.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {
  @GetMapping("/api/login")
  public String login(@RequestParam Optional<String> status) {
    if (status.isPresent() && status.get().equals("error")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return "";
  }
}
