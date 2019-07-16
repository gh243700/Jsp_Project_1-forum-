package com.lee.dao;

import com.lee.beans.Topic;
import com.lee.beans.TopicReply;
import com.lee.beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicReplyDAO extends DataAccessObject<TopicReply> {

  public TopicReplyDAO(Connection connection) {
    super(connection);
  }

  @Override
  public TopicReply readById(int id) {
    String sql =
        "SELECT s.id, s.content, s.created, s.p_topic, s.u_id,"
            + "u.id,u.username,u.first_name,u.last_name,u.email,u.age,u.created_on,u.last_login,u.password,"
            + "t.id,t.user_id,t.post_date,t.title,t.main_content,t.edited,t.replies,t.topic_category"
            + " FROM topic_reply AS s"
            + " JOIN topics AS t ON s.id = t.id"
            + " JOIN users AS u ON s.u_id = u.id "
            + "WHERE s.id =?";
    TopicReply topicReply = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        user.setEmail(resultSet.getString(9));
        user.setAge(resultSet.getInt(10));
        user.setCreated_on(resultSet.getDate(11));
        user.setLast_login(resultSet.getDate(12));
        user.setPassword(resultSet.getString(13));
        topicReply.setUser(user);
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(14));
        topic.setUser_id(resultSet.getInt(15));
        topic.setPost_date(resultSet.getDate(16));
        topic.setTitle(resultSet.getString(17));
        topic.setMain_content(resultSet.getString(18));
        topic.setEdited(resultSet.getBoolean(19));
        topic.setReplies(resultSet.getInt(20));
        topic.setCatagory(resultSet.getInt(21));
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
    String sql =
        "SELECT s.id, s.content, s.created, s.p_topic, s.u_id,"
            + "u.id,u.username,u.first_name,u.last_name,u.email,u.age,u.created_on,u.last_login,u.password,"
            + "t.id,t.user_id,t.post_date,t.title,t.main_content,t.edited,t.replies,t.topic_category"
            + " FROM topic_reply AS s"
            + " JOIN topics AS t ON s.id = t.id"
            + " JOIN users AS u ON s.u_id = u.id";
    List<TopicReply> list = new ArrayList<>();
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        TopicReply topicReply = new TopicReply();
        topicReply.setId(resultSet.getInt(1));
        topicReply.setContent(resultSet.getString(2));
        topicReply.setCreated(resultSet.getDate(3));
        topicReply.setP_topic(resultSet.getInt(4));
        topicReply.setU_id(resultSet.getInt(5));
        User user = new User();
        user.setId(resultSet.getInt(6));
        user.setUsername(resultSet.getString(7));
        user.setFirst_name(resultSet.getString(8));
        user.setEmail(resultSet.getString(9));
        user.setAge(resultSet.getInt(10));
        user.setCreated_on(resultSet.getDate(11));
        user.setLast_login(resultSet.getDate(12));
        user.setPassword(resultSet.getString(13));
        topicReply.setUser(user);
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(14));
        topic.setUser_id(resultSet.getInt(15));
        topic.setPost_date(resultSet.getDate(16));
        topic.setTitle(resultSet.getString(17));
        topic.setMain_content(resultSet.getString(18));
        topic.setEdited(resultSet.getBoolean(19));
        topic.setReplies(resultSet.getInt(20));
        topic.setCatagory(resultSet.getInt(21));
        topicReply.setTopic(topic);
        list.add(topicReply);
      }
    } catch (SQLException e) {
      e.printStackTrace();
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
    String sql = "INSERT INTO topic_reply(content,p_topic,u_id)" + "VALUES(?,?,?)";
    int rowsAffected = 0;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
