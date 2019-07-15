package com.lee.dao;

import com.lee.util.DataTransferObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DataAccessObject<T extends DataTransferObject> {

  protected Connection connection;

  DataAccessObject(Connection connection) {
    this.connection = connection;
  }

  public abstract T readById(int id);

  public abstract List<T> readAll();

  public abstract int delete(T obj);

  public abstract int update(T obj);

  public abstract int create(T obj);
}
