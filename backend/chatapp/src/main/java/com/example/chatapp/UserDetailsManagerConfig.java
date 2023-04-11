package com.example.chatapp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * ユーザー認証情報のDAOとなる{@link UserDetailsManager}を設定するクラス
 */
@Configuration
public class UserDetailsManagerConfig {

  @Autowired
  DataSource dataSource;

  @Bean
  public UserDetailsManager users() {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(this.dataSource);

    // ユーザーを作成するストアドを呼び出すクエリを設定
    // CALL add_user(username, password, enabled)
    jdbcUserDetailsManager.setCreateUserSql("CALL add_user(?,?,?)");

    // ユーザーを取得するクエリを設定
    jdbcUserDetailsManager.setUsersByUsernameQuery(
        "SELECT username, password_hash as password, enabled " +
            "FROM user_authentication t1 INNER JOIN user t2 ON t1.id = t2.id " +
            "WHERE username = ?");

    // ユーザーのロールを作成するクエリを設定
    jdbcUserDetailsManager.setCreateAuthoritySql(
        "INSERT INTO authority VALUES((SELECT id FROM user WHERE username = ?), ?)");

    // ユーザーのロールを取得するクエリを設定
    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
        "SELECT username, authority " +
            "FROM authority t1 INNER JOIN user t2 ON t1.id = t2.id " +
            "WHERE username = ?");

    return jdbcUserDetailsManager;
  }
}
