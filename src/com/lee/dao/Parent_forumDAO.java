package com.lee.dao;

import com.lee.beans.Forum;
import com.lee.beans.Parent_Forum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parent_forumDAO extends DataAccessObject<Parent_Forum> {

  public Parent_forumDAO(Connection connection) {
    super(connection);
  }
  //  private int id;
  //  private String name;
  //  private String description;

  public Parent_Forum readById(int id) {
    final String SQL = "SELECT id,name,description FROM parent_forums WHERE id = ?";
    Parent_Forum parent_forum = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        parent_forum =
            new Parent_Forum(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
      }
      if (parent_forum == null) {
        throw new SQLException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return parent_forum;
  }

  @Override
  public List<Parent_Forum> readAll() {
    List<Parent_Forum> list = new ArrayList<>();
    final String SQL = "SELECT id,name,description " + "FROM parent_forums";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Parent_Forum parent_Forum =
            new Parent_Forum(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        list.add(parent_Forum);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override
  public int delete(Parent_Forum obj) {
    return 0;
  }

  @Override
  public int update(Parent_Forum obj) {
    return 0;
  }

  @Override
  public int create(Parent_Forum obj) {
    return 0;
  }
}
