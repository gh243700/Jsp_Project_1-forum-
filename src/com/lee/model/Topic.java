package com.lee.model;

import java.util.Date;

public class Topic implements DataTransferObject {
  private int id;
  private int topic_category;
  private int user_id;
  private Date post_date;
  private String title;
  private String main_content;
  private boolean edited;
  private int replies;
  private Forum forum;

  private User user;

  public Topic() {}

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  public Topic(
      int id,
      int topic_category,
      int user_id,
      Date post_date,
      String title,
      String main_content,
      boolean edited,
      int replies,
      User user,
      Forum forum) {
    this.id = id;
    this.topic_category = topic_category;
    this.user_id = user_id;
    this.post_date = post_date;
    this.title = title;
    this.main_content = main_content;
    this.edited = edited;
    this.replies = replies;
    this.user = user;
    this.forum = forum;
  }

  @Override
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCatagory() {
    return topic_category;
  }

  public void setCatagory(int topic_category) {
    this.topic_category = topic_category;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public Date getPost_date() {
    return post_date;
  }

  public void setPost_date(Date post_date) {
    this.post_date = post_date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMain_content() {
    return main_content;
  }

  public void setMain_content(String main_content) {
    this.main_content = main_content;
  }

  public boolean getEdited() {
    return edited;
  }

  public void setEdited(boolean edited) {
    this.edited = edited;
  }

  public int getReplies() {
    return replies;
  }

  public void setReplies(int replies) {
    this.replies = replies;
  }

  public Forum getForum() {
    return forum;
  }

  public void setForum(Forum forum) {
    this.forum = forum;
  }

  @Override
  public String toString() {
    return "topic{"
        + "id="
        + id
        + ", topic_category='"
        + topic_category
        + '\''
        + ", user_id='"
        + user_id
        + '\''
        + ", post_date="
        + post_date
        + ", title='"
        + title
        + '\''
        + ", main_content='"
        + main_content
        + '\''
        + ", edited="
        + edited
        + ", replies="
        + replies
        + '}';
  }
}
