package com.example.chatapp.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chatapp.dto.MessageExtended;
import com.example.chatapp.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query(value = "SELECT t1.id, t1.channel_id, t1.content, t1.created_by, t2.username, t1.created_at " +
      "FROM message t1 INNER JOIN user t2 ON t1.created_by = t2.id " +
      "WHERE t1.channel_id = ?1", nativeQuery = true)
  List<Object[]> findByChannelIdQuery(long channelId);

  public default List<MessageExtended> findByChannelId(long channelId) {
    return this.findByChannelIdQuery(channelId)
        .stream()
        .map(MessageExtended::new)
        .collect(Collectors.toList());
  };
}
