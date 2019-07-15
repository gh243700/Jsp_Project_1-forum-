package com.lee.servlet.forum;

import com.lee.beans.Topic;
import com.lee.dao.ForumDao;
import com.lee.dao.TopicDAO;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForumServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // String[] full_url = req.getRequestURI().split("/");

    System.out.println("in :" + getClass());

    if (req.getParameter("do") != null) {
      req.getRequestDispatcher("/Add_Topic").forward(req, resp);
      return;
    }

    String forum = req.getParameter("attr");

    String title = forum.substring(forum.indexOf("-") + 1);
    req.setAttribute("title", title.replace("-", " "));

    String[] info = forum.split("-");
    int PAGE_INDEX = 0;
    if ((req.getParameter("page") != null)) {
      PAGE_INDEX = (Integer.parseInt(req.getParameter("page"))) - 1;
    }

    final int LIMIT = 5;
    final int OFFSET = PAGE_INDEX * LIMIT;
    List<Topic> topics = null;
    String description = null;
    try {
      Connection connection = new DriverConnectionManager().getConnection();
      TopicDAO topicDAO = new TopicDAO(connection);
      ForumDao forumDao = new ForumDao(connection);
      topics = topicDAO.readAll(Integer.parseInt(info[0]), LIMIT, OFFSET);
      description = forumDao.readById(Integer.parseInt(info[0])).getDescription();
      // description = topics.get(0).getForum().getDescription();
    } catch (SQLException | RuntimeException e) {
      e.printStackTrace();
      // req.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(req, resp);
      return;
    }
    int NUMBER_OF_TOPICS = topics.size();
    final int NUMBER_OF_PAGES = NUMBER_OF_TOPICS / LIMIT;
    //
    req.setAttribute("description", description);
    req.setAttribute("PAGE_INDEX", PAGE_INDEX);
    req.setAttribute("MUMBER_OF_PAGES", NUMBER_OF_PAGES);
    req.setAttribute("topics", topics);
    req.getRequestDispatcher("/JSP/forum_page.jsp").forward(req, resp);
  }
}
