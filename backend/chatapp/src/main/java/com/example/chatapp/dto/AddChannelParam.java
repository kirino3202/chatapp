package com.example.chatapp.dto;

import com.example.chatapp.controller.ChannelController;

/**
 * DTO {@link ChannelController#addChannel(AddChannelParam)}
 */
public class AddChannelParam {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
