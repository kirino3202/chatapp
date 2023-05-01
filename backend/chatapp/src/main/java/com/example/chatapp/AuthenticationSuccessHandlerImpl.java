package com.example.chatapp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.chatapp.repository.UserRepository;

/**
 * 認証成功時のハンドラ
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
  @Autowired
  UserRepository userRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    var principal = (UserDetails) authentication.getPrincipal();
    var userId = userRepository.findByUsername(principal.getUsername()).getId();

    var out = response.getWriter();
    response.setContentType("application/json");
    out.print(
        (new StringBuilder()).append("{\"status\":\"success\", \"userId\":").append(userId).append("}").toString());
    out.flush();
  }
}
