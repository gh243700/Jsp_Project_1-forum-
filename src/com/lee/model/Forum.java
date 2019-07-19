package com.lee.model;

public class Forum implements DataTransferObject {

  private int id;
  private String name;
  private String description;
  private int parent_id;
  private Parent_Forum parent_forum;

  public Forum(int id, String name, String description, int parent_id, Parent_Forum parent_forum) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.parent_id = parent_id;
    this.parent_forum = parent_forum;
  }

  public Forum() {}

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getParent_id() {
    return parent_id;
  }

  public void setParent_id(int parent_id) {
    this.parent_id = parent_id;
  }

  public Parent_Forum getParent_forum() {
    return parent_forum;
  }

  public void setParent_forum(Parent_Forum parent_forum) {
    this.parent_forum = parent_forum;
  }

  @Override
  public String toString() {
    return "Forum{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", parent_id="
        + parent_id
        + ", parent_forum="
        + parent_forum
        + '}';
  }
}
