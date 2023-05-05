package com.example.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.chatapp.dto.RegisterParam;

/**
 * ユーザー登録のためのController
 */
@RestController
@RequestMapping("/api/register")
public class RegisterController {

  @Autowired
  UserDetailsManager userDetailsManager;

  @Autowired
  PasswordEncoder passwordEncoder;

  @PostMapping
  public void register(@RequestBody @Validated RegisterParam param) {
    if (userDetailsManager.userExists(param.getUsername())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    userDetailsManager.createUser(
        User.withUsername(param.getUsername())
            .password(passwordEncoder.encode(param.getPassword()))
            .authorities("USER")
            .build());

  }
}
