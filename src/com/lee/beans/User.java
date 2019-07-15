package com.lee.beans;

import com.lee.util.DataTransferObject;
import java.util.Date;

public class User implements DataTransferObject {

  private int id;
  private String username;
  private String first_name;
  private String last_name;
  private String email;
  private int age;
  private Date created_on;
  private Date last_login;
  private String password;

  public User(
      int id,
      String username,
      String first_name,
      String last_name,
      String email,
      int age,
      Date created_on,
      Date last_login,
      String password) {
    this.id = id;
    this.username = username;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.age = age;
    this.created_on = created_on;
    this.last_login = last_login;
    this.password = password;
  }

  public User() {}

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Date getCreated_on() {
    return created_on;
  }

  public void setCreated_on(Date created_on) {
    this.created_on = created_on;
  }

  public Date getLast_login() {
    return last_login;
  }

  public void setLast_login(Date last_login) {
    this.last_login = last_login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", first_name='"
        + first_name
        + '\''
        + ", last_name='"
        + last_name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", age="
        + age
        + ", created_on="
        + created_on
        + ", last_login="
        + last_login
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
