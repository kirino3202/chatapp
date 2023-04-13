package com.example.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatapp.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
  public List<Message> findByChannelId(long channelId);
}
