package com.lee.repository;

import com.lee.model.Parent_Forum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentForumRepository extends DataAccessObject<Parent_Forum> {

  private final String READ_BY_ID = "SELECT id,name,description FROM parent_forums WHERE id = ?";
  private final String READ_ALL = "SELECT id,name,description " + "FROM parent_forums";

  public ParentForumRepository(Connection connection) {
    super(connection);
  }

  public Parent_Forum readById(int id) {
    Parent_Forum parent_forum = null;
    try (PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        parent_forum =
            new Parent_Forum(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return parent_forum;
  }

  @Override
  public List<Parent_Forum> readAll() {
    List<Parent_Forum> list = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Parent_Forum parent_Forum =
            new Parent_Forum(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        list.add(parent_Forum);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
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
