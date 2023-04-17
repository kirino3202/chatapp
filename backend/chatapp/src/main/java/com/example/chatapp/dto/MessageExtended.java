package com.example.chatapp.dto;

import java.util.Date;

public class MessageExtended {
  private long id;

  private long channelId;

  private String content;

  private long createdBy;

  private String createdByUsername;

  private Date createdAt;

  public MessageExtended() {
  }

  public MessageExtended(Object[] obj) {
    this.id = ((Integer) obj[0]).longValue();
    this.channelId = ((Integer) obj[1]).longValue();
    this.content = (String) obj[2];
    this.createdBy = ((Integer) obj[3]).longValue();
    this.createdByUsername = (String) obj[4];
    this.createdAt = (Date) obj[5];
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getChannelId() {
    return channelId;
  }

  public void setChannelId(long channelId) {
    this.channelId = channelId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedByUsername() {
    return createdByUsername;
  }

  public void setCreatedByUsername(String createdByUsername) {
    this.createdByUsername = createdByUsername;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
