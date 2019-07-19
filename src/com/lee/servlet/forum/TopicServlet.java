package com.lee.servlet.forum;

import com.lee.model.Topic;
import com.lee.model.TopicReply;
import com.lee.repository.TopicRepository;
import com.lee.repository.TopicReplyRepository;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopicServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("doGet()->" + getClass());

    TopicRepository topicRepository = (TopicRepository) req.getAttribute("topicRepository");
    TopicReplyRepository topicReplyRepository = (TopicReplyRepository) req.getAttribute("topicReplyRepository");

    Topic topic = topicRepository.readById((int) req.getAttribute("topic_id"));
    List<TopicReply> topicReplyList = topicReplyRepository.readAll((int) req.getAttribute("topic_id"));
    req.setAttribute("topicUri",req.getAttribute("topicUri"));
    req.setAttribute("topicReplyList", topicReplyList);
    req.setAttribute("topic", topic);
    req.getRequestDispatcher("/JSP/Topic.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("doPost()" +getClass().getName());
    TopicReplyRepository topicReplyRepository = (TopicReplyRepository) req.getAttribute("topicReplyRepository");
    TopicReply topicReply =(TopicReply) req.getAttribute("topicReply");

    String topicUri =req.getParameter("topicUri");

    topicReplyRepository.create(topicReply);
    resp.sendRedirect(topicUri);

  }
}
