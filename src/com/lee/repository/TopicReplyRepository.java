package com.lee.repository;

import com.lee.model.Topic;
import com.lee.model.TopicReply;
import com.lee.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicReplyRepository extends DataAccessObject<TopicReply> {

  private final String CREATE = "INSERT INTO topic_reply(content,p_topic,u_id)" + "VALUES(?,?,?)";

  private final String READ_BY_ID =
      "SELECT s.id, s.content, s.created, s.p_topic, s.u_id,"
          + "u.id,u.username,u.first_name,u.last_name,u.email,u.age,u.created_on,u.last_login,u.password,"
          + "t.id,t.user_id,t.post_date,t.title,t.main_content,t.edited,t.replies,t.topic_category"
          + " FROM topic_reply AS s"
          + " JOIN topics AS t ON s.id = t.id"
          + " JOIN users AS u ON s.u_id = u.id "
          + "WHERE s.id =?";

  private final String READ_ALL =
      "SELECT s.id, s.content, s.created, s.p_topic, s.u_id,"
          + "u.id,u.username,u.first_name,u.last_name,u.email,u.age,u.created_on,u.last_login,u.password,"
          + "t.id,t.user_id,t.post_date,t.title,t.main_content,t.edited,t.replies,t.topic_category"
          + " FROM topic_reply AS s"
          + " JOIN topics AS t ON s.p_topic = t.id"
          + " JOIN users AS u ON s.u_id = u.id"
          + " WHERE s.p_topic = ?";

  public TopicReplyRepository(Connection connection) {
    super(connection);
  }

  @Override
  public TopicReply readById(int id) {

    TopicReply topicReply = null;
    try (PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        topicReply = new TopicReply();
        topicReply.setId(resultSet.getInt(1));
        topicReply.setContent(resultSet.getString(2));
        topicReply.setCreated(resultSet.getDate(3));
        topicReply.setP_topic(resultSet.getInt(4));
        topicReply.setU_id(resultSet.getInt(5));
        User user = new User();
        user.setId(resultSet.getInt(6));
        user.setUsername(resultSet.getString(7));
        user.setFirst_name(resultSet.getString(8));
        user.setLast_name(resultSet.getString(9));
        user.setEmail(resultSet.getString(10));
        user.setAge(resultSet.getInt(11));
        user.setCreated_on(resultSet.getDate(12));
        user.setLast_login(resultSet.getDate(13));
        user.setPassword(resultSet.getString(14));
        topicReply.setUser(user);
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(15));
        topic.setUser_id(resultSet.getInt(16));
        topic.setPost_date(resultSet.getDate(17));
        topic.setTitle(resultSet.getString(18));
        topic.setMain_content(resultSet.getString(19));
        topic.setEdited(resultSet.getBoolean(20));
        topic.setReplies(resultSet.getInt(21));
        topic.setCatagory(resultSet.getInt(22));
        topicReply.setTopic(topic);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return topicReply;
  }

  @Override
  public List<TopicReply> readAll() {
    return null;
  }

  public List<TopicReply> readAll(int p_topic) {

    List<TopicReply> list = new ArrayList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL)) {
      preparedStatement.setInt(1, p_topic);
      ResultSet resultSet = preparedStatement.executeQuery();
      int id = 0;
      TopicReply topicReply = null;
      while (resultSet.next()) {
        if (id == 0) {
          topicReply = new TopicReply();
          topicReply.setId(resultSet.getInt(1));
          topicReply.setContent(resultSet.getString(2));
          topicReply.setCreated(resultSet.getDate(3));
          topicReply.setP_topic(resultSet.getInt(4));
          id = topicReply.getP_topic();
          topicReply.setU_id(resultSet.getInt(5));
        }
        User user = new User();
        user.setId(resultSet.getInt(6));
        user.setUsername(resultSet.getString(7));
        user.setFirst_name(resultSet.getString(8));
        user.setLast_name(resultSet.getString(9));
        user.setEmail(resultSet.getString(10));
        user.setAge(resultSet.getInt(11));
        user.setCreated_on(resultSet.getDate(12));
        user.setLast_login(resultSet.getDate(13));
        user.setPassword(resultSet.getString(14));
        topicReply.setUser(user);
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(15));
        topic.setUser_id(resultSet.getInt(16));
        topic.setPost_date(resultSet.getDate(17));
        topic.setTitle(resultSet.getString(18));
        topic.setMain_content(resultSet.getString(19));
        topic.setEdited(resultSet.getBoolean(20));
        topic.setReplies(resultSet.getInt(21));
        topic.setCatagory(resultSet.getInt(22));
        topicReply.setTopic(topic);
        list.add(topicReply);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return list;
  }

  @Override
  public int delete(TopicReply obj) {
    return 0;
  }

  @Override
  public int update(TopicReply obj) {
    return 0;
  }

  @Override
  public int create(TopicReply obj) {
    int rowsAffected;
    try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
      preparedStatement.setString(1, obj.getContent());
      preparedStatement.setInt(2, obj.getP_topic());
      preparedStatement.setInt(3, obj.getU_id());
      rowsAffected = preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return rowsAffected;
  }
}
