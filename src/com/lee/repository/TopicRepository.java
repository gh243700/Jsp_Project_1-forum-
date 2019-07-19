package com.lee.repository;

import com.lee.model.Forum;
import com.lee.model.Topic;
import com.lee.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicRepository extends DataAccessObject<Topic> {

  public TopicRepository(Connection connection) {
    super(connection);
  }

  @Override
  public Topic readById(int id) {
    String sql =
        "SELECT t.id,t.topic_category,t.user_id,t.post_date,t.title,t.main_content,t.edited,t.replies,"
            + "u.id,u.username,u.first_name,u.last_name,u.email,u.age,u.created_on,u.last_login,u.password,"
            + "f.id,f.name,f.description,f.parent_id"
            + " FROM topics AS t JOIN users AS u ON u.id =t.user_id JOIN forums AS f ON f.id = t.topic_category WHERE t.id = ?";
    Topic topic = null;
    try {
      PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Forum forum = new Forum();
        forum.setId(resultSet.getInt(18));
        forum.setName(resultSet.getString(19));
        forum.setDescription(resultSet.getString(20));
        forum.setParent_id(resultSet.getInt(21));

        User user = new User();
        user.setId(resultSet.getInt(9));
        user.setUsername(resultSet.getString(10));
        user.setFirst_name(resultSet.getString(11));
        user.setLast_name(resultSet.getString(12));
        user.setEmail(resultSet.getString(13));
        user.setAge(resultSet.getInt(14));
        user.setCreated_on(resultSet.getDate(15));
        user.setLast_login(resultSet.getDate(16));
        user.setPassword(resultSet.getString(17));
        topic =
            new Topic(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getDate(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getBoolean(7),
                resultSet.getInt(8),
                user,
                forum);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return topic;
  }

  @Override
  public List<Topic> readAll() {
    return null;
  }

  public List<Topic> readAll(int topic_category, int limit, int offset) {

    List<Topic> list = new ArrayList<>();

    String sql =
        "SELECT t.id,t.topic_category,t.user_id,t.post_date,t.title,t.main_content,t.edited,t.replies,"
            + "u.id,u.username,u.first_name,u.last_name,u.email,u.age,u.created_on,u.last_login,u.password "
            + "FROM topics AS t INNER JOIN users AS u ON t.user_id = u.id WHERE t.topic_category = ? LIMIT ? OFFSET ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, topic_category);
      preparedStatement.setInt(2, limit);
      preparedStatement.setInt(3, offset);
      ResultSet resultSet = preparedStatement.executeQuery();
      Topic topic = null;

      while (resultSet.next()) {
        topic = new Topic();

        topic.setId(resultSet.getInt(1));
        topic.setCatagory(resultSet.getInt(2));
        topic.setUser_id(resultSet.getInt(3));
        topic.setPost_date(resultSet.getDate(4));
        topic.setTitle(resultSet.getString(5));
        topic.setMain_content(resultSet.getString(6));
        topic.setEdited(resultSet.getBoolean(7));
        topic.setReplies(resultSet.getInt(8));
        topic.setUser(
            new User(
                resultSet.getInt(9),
                resultSet.getString(10),
                resultSet.getString(11),
                resultSet.getString(12),
                resultSet.getString(13),
                resultSet.getInt(14),
                resultSet.getDate(15),
                resultSet.getDate(16),
                resultSet.getString(17)));
        list.add(topic);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  @Override
  public int delete(Topic obj) {
    return 0;
  }

  @Override
  public int update(Topic obj) {
    return 0;
  }

  @Override
  public int create(Topic obj) {
    String sql =
        "INSERT INTO topics (topic_category,user_id,title,main_content,post_date)"
            + "VALUES (?,?,?,?,?)";
    int rowsAffected;
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      // statement.setInt(1,obj.getId());
      statement.setInt(1, obj.getCatagory());
      statement.setInt(2, obj.getUser_id());
      // statement.setDate(4, (Date)obj.getPost_date());
      statement.setString(3, obj.getTitle());
      statement.setString(4, obj.getMain_content());
      Date date = new Date(obj.getPost_date().getTime());
      statement.setDate(5, (date));
      // statement.setBoolean(7,obj.getEdited());
      // statement.setInt(8,obj.getReplies());
      rowsAffected = statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return rowsAffected;
  }
}
