package com.lee.repository.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverConnectionManager {
  private final String URL;
  private final Properties properties;

  public DriverConnectionManager()
  {
    String host = "localhost";
    String database = "project_01_forum";
    this.URL = "jdbc:postgresql://"+host+"/"+database;
    properties = new Properties();
    properties.setProperty("user","postgres");
    properties.setProperty("password","admin");
  }
  public Connection getConnection() throws SQLException{
    return DriverManager.getConnection(URL,properties);
  }

}
