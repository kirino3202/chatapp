package com.example.chatapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.chatapp.dto.AddChannelParam;
import com.example.chatapp.entity.Channel;
import com.example.chatapp.repository.ChannelRepository;
import com.example.chatapp.repository.UserRepository;

@RestController
@RequestMapping("/api/channel")
public class ChannelController {

  private final ChannelRepository channelRepository;

  private final UserRepository userRepository;

  public ChannelController(
      ChannelRepository channelRepository,
      UserRepository userRepository) {
    this.channelRepository = channelRepository;
    this.userRepository = userRepository;
  }

  /**
   * すべてのChannelを取得する
   */
  @GetMapping
  public List<Channel> getChannels() {
    return this.channelRepository.findAll();
  }

  /**
   * Channelを追加する
   */
  @PostMapping
  public Channel addChannel(@RequestBody AddChannelParam param, Principal principal) {

    var userId = userRepository.findByUsername(principal.getName()).getId();
    var channel = new Channel();

    channel.setName(param.getName());
    channel.setCreatedBy(userId);

    return channelRepository.save(channel);
  }

  /**
   * Channelを削除する
   */
  @DeleteMapping("/{channelId}")
  public void deleteChannel(@PathVariable("channelId") long id, Principal principal) {
    var channel = channelRepository.findById(id);

    if (channel.isPresent()) {
      var userId = userRepository.findByUsername(principal.getName()).getId();
      if (channel.get().getCreatedBy() == userId) {
        // ログインユーザーがChannelの作成者であれば削除する
        channelRepository.deleteById(id);

      } else {
        // ログインユーザーがChannelの作成者でなければ403を返す
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      }
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}
