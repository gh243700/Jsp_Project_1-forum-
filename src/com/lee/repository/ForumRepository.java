package com.lee.repository;

import com.lee.model.Forum;
import com.lee.model.Parent_Forum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForumRepository extends DataAccessObject<Forum> {

  public ForumRepository(Connection connection) {
    super(connection);
  }

  private final String READ_BY_ID =
      "SELECT c.id,c.name,c.description,c.parent_id,p.id,p.name,p.description "
          + "FROM forums AS c JOIN parent_forums AS p ON c.parent_id = p.id  WHERE c.id = ?";

  private final String READ_ALL =
      "SELECT c.id,c.name,c.description,c.parent_id,p.id,p.name,p.description "
          + "FROM forums AS c JOIN parent_forums AS p ON c.parent_id = p.id";

  @Override
  public Forum readById(int val) {

    Forum forum = null;
    try (PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
      preparedStatement.setInt(1, val);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        forum = new Forum();
        forum.setId(resultSet.getInt(1));
        forum.setName(resultSet.getString(2));
        forum.setDescription(resultSet.getString(3));
        forum.setParent_id(resultSet.getInt(4));
        Parent_Forum parent_forum = new Parent_Forum();
        parent_forum.setId(resultSet.getInt(5));
        parent_forum.setName(resultSet.getString(6));
        parent_forum.setDescription(resultSet.getString(7));
        forum.setParent_forum(parent_forum);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return forum;
  }

  @Override
  public List<Forum> readAll() {
    List<Forum> list = new ArrayList<>();

    Forum forum = null;
    try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
      ResultSet resultSet = preparedStatement.executeQuery();
      int id = 0;
      Parent_Forum parent_forum = null;
      while (resultSet.next()) {
        forum = new Forum();
        forum.setId(resultSet.getInt(1));
        forum.setName(resultSet.getString(2));
        forum.setDescription(resultSet.getString(3));
        forum.setParent_id(resultSet.getInt(4));
        if (id == 0) {
          parent_forum = new Parent_Forum();
          parent_forum.setId(resultSet.getInt(5));
          id = parent_forum.getId();
          parent_forum.setName(resultSet.getString(6));
          parent_forum.setDescription(resultSet.getString(7));
        }
        forum.setParent_forum(parent_forum);
        list.add(forum);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
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
}
