package com.example.chatapp.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.chatapp.dto.AddMessageParam;
import com.example.chatapp.dto.MessageExtended;
import com.example.chatapp.entity.Channel;
import com.example.chatapp.entity.Message;
import com.example.chatapp.repository.ChannelRepository;
import com.example.chatapp.repository.MessageRepository;
import com.example.chatapp.repository.UserRepository;

/**
 * メッセージの取得、登録、削除を実行するController
 */
@RestController
@RequestMapping("/api/channel/{channelId}/message")
public class MessageController {

  private final ChannelRepository channelRepository;

  private final MessageRepository messageRepository;

  private final UserRepository userRepository;

  public MessageController(
      ChannelRepository channelRepository,
      MessageRepository messageRepository,
      UserRepository userRepository) {

    this.channelRepository = channelRepository;
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<MessageExtended> getMessages(@PathVariable long channelId) {
    this.getOrThrowChannel(channelId);
    return messageRepository.findByChannelId(channelId);
  }

  @PostMapping
  public Message addMessage(
      @PathVariable long channelId,
      @RequestBody @Validated AddMessageParam param,
      Principal principal) {

    this.getOrThrowChannel(channelId);

    var userId = userRepository.findByUsername(principal.getName()).getId();

    var message = new Message();
    message.setChannelId(channelId);
    message.setContent(param.getContent());
    message.setCreatedBy(userId);
    message.setCreatedAt(new Date());

    return this.messageRepository.save(message);
  }

  @DeleteMapping("/{messageId}")
  public void deleteMessage(
      @PathVariable long channelId,
      @PathVariable long messageId,
      Principal principal) {

    var userId = userRepository.findByUsername(principal.getName()).getId();
    var channel = getOrThrowChannel(channelId);
    var message = messageRepository.findById(messageId);

    if (!message.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    if (channel.getCreatedBy() != userId
        && message.get().getCreatedBy() != userId) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    messageRepository.deleteById(messageId);
  }

  private Channel getOrThrowChannel(long channelId) {
    var channel = channelRepository.findById(channelId);
    if (!channel.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return channel.get();
  }
}
