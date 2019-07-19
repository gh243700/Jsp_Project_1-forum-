package com.lee.servlet.forum;

import com.lee.model.Forum;
import com.lee.model.Parent_Forum;
import com.lee.repository.ForumRepository;
import com.lee.repository.ParentForumRepository;
import com.lee.repository.util.DriverConnectionManager;
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
      ParentForumRepository parent_forumRepository = new ParentForumRepository(connection);
      ForumRepository forumRepository = new ForumRepository(connection);
      parent_forums = parent_forumRepository.readAll();
      forums = forumRepository.readAll();
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
