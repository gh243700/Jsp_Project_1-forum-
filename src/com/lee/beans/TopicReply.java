package com.lee.beans;

import com.lee.util.DataTransferObject;
import java.util.Date;

public class TopicReply implements DataTransferObject{
  private int id;
  private String content;
  private Date created;
  private int p_topic;
  private int u_id;
  private Topic topic;
  private User user;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public int getP_topic() {
    return p_topic;
  }

  public void setP_topic(int p_topic) {
    this.p_topic = p_topic;
  }

  public int getU_id() {
    return u_id;
  }

  public void setU_id(int u_id) {
    this.u_id = u_id;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
