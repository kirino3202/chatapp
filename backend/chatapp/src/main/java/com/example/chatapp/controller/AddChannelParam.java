package com.example.chatapp.controller;

/**
 * DTO {@link ChannelController#addChannel(AddChannelParam)}
 */
public class AddChannelParam {
  String name;

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }
}
