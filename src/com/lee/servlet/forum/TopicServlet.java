package com.lee.servlet.forum;

import com.lee.beans.Topic;
import com.lee.dao.TopicDAO;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopicServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("in :" + getClass());

    String[] attr = req.getParameter("attr").split("-");

    int topic_id = Integer.parseInt(attr[0]);
    System.out.println(topic_id);
    TopicDAO topicDAO = null;
    RequestDispatcher requestDispatcher_Error = req.getRequestDispatcher("/JSP/ErrorPage.jsp");
    Topic topic = null;
    try {
      topicDAO = new TopicDAO(new DriverConnectionManager().getConnection());
      topic = topicDAO.readById(topic_id);
      if (topic == null) {
        throw new SQLException();
      }
    } catch (SQLException e) {
      requestDispatcher_Error.forward(req, resp);
    }
    System.out.println(topic);

    req.setAttribute("topic", topic);
    req.getRequestDispatcher("/JSP/Topic.jsp").forward(req, resp);
  }
}
