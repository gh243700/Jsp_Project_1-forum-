package com.lee.servlet.forum;

import com.lee.beans.Topic;
import com.lee.beans.User;
import com.lee.dao.TopicDAO;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Add_Topic")
public class AddTopicServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String category = req.getParameter("category");
    System.out.println("category :"+category+"-----------------------------");
    req.setAttribute("category",category);
    req.getRequestDispatcher("/JSP/Add_Discussion.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("-------do post");
    System.out.println(getClass() + "----------------------DOPOST-----------------");
    String category = req.getParameter("category").replace("-"," ");
    String title = req.getParameter("title");
    String tag = req.getParameter("tags");
    String content = req.getParameter("content");
    Topic topic = null;
    if (category != null && title != null && content != null) {
      User user = (User) req.getSession().getAttribute("user");
      topic = new Topic();
      topic.setCatagory(Integer.parseInt(category));
      topic.setMain_content(content);
      topic.setTitle(title);
      topic.setUser_id(user.getId());
      topic.setPost_date(new Date(System.currentTimeMillis()));
    }
    Connection connection = null;
    int checkIfSuccess = 0;
    try {
      connection = new DriverConnectionManager().getConnection();
      TopicDAO topicDAO = new TopicDAO(connection);
      if (topic != null) {
        checkIfSuccess = topicDAO.create(topic);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String message = null;
    if (checkIfSuccess == 0) {
      message = "something went wrong";
    } else {
      message = "ok";
    }
    System.out.println(message);
    resp.sendRedirect("http://localhost:8080/project_web/main");
  }
}
