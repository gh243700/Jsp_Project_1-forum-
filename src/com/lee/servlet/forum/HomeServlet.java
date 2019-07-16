package com.lee.servlet.forum;

import com.lee.beans.Forum;
import com.lee.beans.Parent_Forum;
import com.lee.dao.ForumDao;
import com.lee.dao.Parent_forumDAO;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("in :" + getClass());

    List<Forum> forums = null;
    List<Parent_Forum> parent_forums = null;
    try {
      Connection connection = new DriverConnectionManager().getConnection();
      Parent_forumDAO parent_forumDAO = new Parent_forumDAO(connection);
      ForumDao forumDAO = new ForumDao(connection);
      parent_forums = parent_forumDAO.readAll();
      forums = forumDAO.readAll();
    } catch (SQLException e) {
      req.getRequestDispatcher("/JSP/ErrorPage.jsp");
    }
    if (forums == null || parent_forums == null) {
      req.getRequestDispatcher("/JSP/ErrorPage.jsp");
    }
    req.setAttribute("parent_forums", parent_forums);
    req.setAttribute("forums", forums);
    req.getRequestDispatcher("/JSP/index.jsp").forward(req, resp);
  }
}
