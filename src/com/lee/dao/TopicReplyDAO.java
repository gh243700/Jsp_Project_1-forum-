package com.lee.dao;

import com.lee.beans.TopicReply;
import com.lee.util.DataTransferObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class TopicReplyDAO extends DataAccessObject<TopicReply> {

  public TopicReplyDAO(Connection connection) {
    super(connection);
  }

  @Override
  public TopicReply readById(int id) {

    return null;
  }

  @Override
  public List<TopicReply> readAll() {
    String sql =
        "SELECT s.id, s.content, s.created, s.p_topic, s.u_id FROM topic_reply AS s"
            + " JOIN topics AS t ON s.id = t.id"
            + " JOIN users AS u ON s.u_id = u.id"
            + "WHERE ";
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
    return 0;
  }
}
