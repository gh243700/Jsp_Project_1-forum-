package com.lee.servlet.forum;

import com.lee.beans.Topic;
import com.lee.beans.TopicReply;
import com.lee.dao.TopicDAO;
import com.lee.dao.TopicReplyDAO;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TopicServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("in :" + getClass());

    String[] attr = req.getParameter("attr").split("-");

    int topic_id = Integer.parseInt(attr[0]);
    System.out.println(topic_id);
    TopicDAO topicDAO = null;
    TopicReplyDAO topicReply = null;
    RequestDispatcher requestDispatcher_Error = req.getRequestDispatcher("/JSP/ErrorPage.jsp");
    Topic topic = null;
    List<TopicReply> topicReplies = null;
    try {
      Connection connection = new DriverConnectionManager().getConnection();
      topicDAO = new TopicDAO(connection);
      topicReply = new TopicReplyDAO(connection);
    } catch (SQLException e) {
      requestDispatcher_Error.forward(req, resp);
    }
    topic = topicDAO.readById(topic_id);
    System.out.println(topic);

    req.setAttribute("topic", topic);
    req.getRequestDispatcher("/JSP/Topic.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session =req.getSession();
    String content = req.getParameter("content");
    int user_id = (int)session.getAttribute("userId");
    int topic_id = Integer.parseInt(req.getParameter("topic_id"));
  }
}
