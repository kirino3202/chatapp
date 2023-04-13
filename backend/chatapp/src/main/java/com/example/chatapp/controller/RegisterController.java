package com.example.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    userDetailsManager.createUser(
        User.withUsername(param.username)
            .password(passwordEncoder.encode(param.password))
            .authorities("USER")
            .build());

  }
}
