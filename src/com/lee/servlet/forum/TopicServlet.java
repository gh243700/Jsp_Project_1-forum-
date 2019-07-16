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
    System.out.println("doGet()->" + getClass());

    TopicDAO topicDAO = (TopicDAO) req.getAttribute("topicDAO");
    TopicReplyDAO topicReplyDAO = (TopicReplyDAO) req.getAttribute("topicReplyDAO");

    Topic topic = topicDAO.readById((int)req.getAttribute("topic_id"));
    List<TopicReply> topicReplyList = topicReplyDAO.readAll((int)req.getAttribute("topic_id"));

    req.setAttribute("topicReplyList",topicReplyList);
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
