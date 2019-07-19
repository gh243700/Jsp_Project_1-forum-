package com.lee.repository;

import com.lee.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends DataAccessObject<User> {

  public UserRepository(Connection connection) {
    super(connection);
  }

  @Override
  public User readById(int id) {
    User user = null;
    String sql =
        "SELECT username,first_name,last_name,email,age,created_on,password FROM users WHERE id = ?";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user = new User();
        user.setUsername(resultSet.getString(1));
        user.setFirst_name(resultSet.getString(2));
        user.setLast_name(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setAge(resultSet.getInt(5));
        user.setCreated_on(resultSet.getDate(6));
        user.setPassword(resultSet.getString(7));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  public Integer getIdByusernameOrEmail(String info) {
    String sql = "SELECT id FROM users WHERE username = ? OR email = ?";
    Integer id = null;
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, info);
      preparedStatement.setString(2, info);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        id = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }

  @Override
  public List<User> readAll() {
    return null;
  }

  @Override
  public int delete(User obj) {
    return 0;
  }

  @Override
  public int update(User obj) {
    return 0;
  }

  public boolean checkifvaliduser(String emailOrname, String password) {
    boolean check = false;
    String sql = "SELECT *FROM users WHERE (username = ? OR email = ?) AND password =?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, emailOrname);
      preparedStatement.setString(2, emailOrname);
      preparedStatement.setString(3, password);
      ResultSet resultSet = preparedStatement.executeQuery();
      String username = null;
      while (resultSet.next()) {
        username = resultSet.getString("username");
        if (username != null) {
          check = true;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return check;
  }

  public <T> boolean CheckifExists(T val, String comumn) {
    String sql = "SELECT *FROM users WHERE " + comumn + " =?";
    boolean check = false;
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setObject(1, val);
      ResultSet resultSet = statement.executeQuery();

      int id = -1;
      while (resultSet.next()) {
        id = resultSet.getInt("id");
      }
      if (id == -1) {
        check = true;
      }
      System.out.println(check);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return check;
  }

  @Override
  public int create(User obj) {
    String sql =
        "INSERT INTO users(username,first_name,last_name,email,age,created_on,password) VALUES (?,?,?,?,?,?,?)";
    int rowsAffected = 0;

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      if (obj != null) {
        preparedStatement.setString(1, obj.getUsername());
        preparedStatement.setString(2, obj.getFirst_name());
        preparedStatement.setString(3, obj.getLast_name());
        preparedStatement.setString(4, obj.getEmail());
        preparedStatement.setInt(5, obj.getAge());
        preparedStatement.setDate(6, new Date(obj.getCreated_on().getTime()));
        preparedStatement.setString(7, obj.getPassword());
        rowsAffected = preparedStatement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    System.out.println(rowsAffected);
    return rowsAffected;
  }
}
