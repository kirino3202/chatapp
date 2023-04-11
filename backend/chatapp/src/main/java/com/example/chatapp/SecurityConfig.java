package com.example.chatapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * セキュリティに関する設定クラス
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // CSRF無効化
    http.csrf((csrf) -> {
      csrf.disable();
    });

    /*
     * URLごとのアクセス制御設定
     */
    http.authorizeHttpRequests((authz) -> {
      authz
          .requestMatchers("/api/register").permitAll()
          .anyRequest().permitAll();
    });

    // ログインフォーム設定
    http.formLogin((formLogin) -> {
      formLogin
          .loginProcessingUrl("/login")
          .usernameParameter("username")
          .passwordParameter("password");
    });

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
