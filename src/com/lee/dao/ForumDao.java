package com.lee.dao;

import com.lee.beans.Forum;
import com.lee.beans.Parent_Forum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumDao extends DataAccessObject<Forum> {

  public ForumDao(Connection connection) {
    super(connection);
  }

  @Override
  public Forum readById(int val) {
    final String SQL =
        "SELECT c.id,c.name,c.description,c.parent_id,p.id,p.name,p.description "
            + "FROM forums AS c JOIN parent_forums AS p ON c.parent_id = p.id  WHERE c.id = ?";

    Forum forum = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);
      preparedStatement.setInt(1, val);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Parent_Forum parent_forum =
            new Parent_Forum(resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
        forum =
            new Forum(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                parent_forum);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return forum;
  }

  @Override
  public List<Forum> readAll() {
    List<Forum> list = new ArrayList<>();
    final String SQL =
        "SELECT c.id,c.name,c.description,c.parent_id,p.id,p.name,p.description "
            + "FROM forums AS c JOIN parent_forums AS p ON c.parent_id = p.id";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(SQL);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Parent_Forum parent_forum =
            new Parent_Forum(resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
        Forum forum =
            new Forum(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                parent_forum);
        list.add(forum);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override
  public int delete(Forum obj) {
    return 0;
  }

  @Override
  public int update(Forum obj) {
    return 0;
  }

  @Override
  public int create(Forum obj) {
    return 0;
  }

  //  public <T>Forum readValue(T val) {
  //    final String SQL =
  //        "SELECT c.id,c.name,c.description,c.parent_id,p.id,p.name,p.description "
  //            + "FROM forums AS c JOIN parent_forums AS p ON c.id = p.id  WHERE c.id = ? OR c.name
  // = ?";
  //    Forum forum = null;
  //    try {
  //      PreparedStatement preparedStatement = connection.prepareStatement(SQL);
  //      preparedStatement.setObject(1, val);
  //      preparedStatement.setObject(2,val);
  //      ResultSet resultSet = preparedStatement.executeQuery();
  //      while (resultSet.next()) {
  //        Parent_Forum parent_forum =
  //            new Parent_Forum(resultSet.getInt(5), resultSet.getString(6),
  // resultSet.getString(7));
  //        forum =
  //            new Forum(
  //                resultSet.getInt(1),
  //                resultSet.getString(2),
  //                resultSet.getString(3),
  //                resultSet.getInt(4),
  //                parent_forum);
  //      }
  //    } catch (SQLException e) {
  //      throw new RuntimeException(e);
  //    }
  //    return forum;
  //  }
}
