package com.lee.servlet.filter;

import com.lee.model.TopicReply;
import com.lee.repository.TopicRepository;
import com.lee.repository.TopicReplyRepository;
import com.lee.repository.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TopicFilter implements Filter {

  private Connection connection;
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println(getClass().getName() + "->init()");

  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    System.out.println(getClass().getName() + "->doFilter()");
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    RequestDispatcher requestDispatcher_Error = httpRequest.getRequestDispatcher("/JSP/ErrorPage.jsp");
    if (httpRequest.getMethod().equals("GET")) {
      String topicUri  = httpRequest.getRequestURI()+"?attr="+httpRequest.getParameter("attr");
      System.out.println("----------------GET-------------"+getClass().getName());
      String[] attr = httpRequest.getParameter("attr").split("-");
      int topic_id = Integer.parseInt(attr[0]);
      System.out.println(topic_id);
      TopicRepository topicRepository = null;
      TopicReplyRepository topicReplyRepository = null;
      try {
        Connection connection = new DriverConnectionManager().getConnection();
        topicRepository = new TopicRepository(connection);
        topicReplyRepository = new TopicReplyRepository(connection);
      } catch (SQLException e) {
        requestDispatcher_Error.forward(httpRequest, servletResponse);
        return;
      }
      httpRequest.setAttribute("topicUri",topicUri);
      httpRequest.setAttribute("topic_id",topic_id);
      httpRequest.setAttribute("topicRepository", topicRepository);
      httpRequest.setAttribute("topicReplyRepository", topicReplyRepository);
    }
    if (httpRequest.getMethod().equals("POST") && httpRequest.getParameter("btn_submit").equals("submit")) {
      System.out.println("----------------POST-------------"+getClass().getName());
      int topic_id = Integer.parseInt(httpRequest.getParameter("topic_id"));
      String content = httpRequest.getParameter("content");
      HttpSession session = httpRequest.getSession();
      //      Date current = new Date();
//      SimpleDateFormat currentSimpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM-SS");
//      currentSimpleDateFormat.format(current);
      TopicReplyRepository topicReplyRepository = null;
      try{
        Connection connection = new DriverConnectionManager().getConnection();
        topicReplyRepository = new TopicReplyRepository(connection);
      }catch (SQLException e){
        requestDispatcher_Error.forward(httpRequest, servletResponse);
        return;
      }
      TopicReply topicReply = new TopicReply();
      topicReply.setContent(content);
      topicReply.setP_topic(topic_id);
      topicReply.setU_id((int)session.getAttribute("userId"));

      httpRequest.setAttribute("topicReplyRepository", topicReplyRepository);
      httpRequest.setAttribute("topicReply",topicReply);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    System.out.println(getClass().getName() + "->destroy()");
  }
}
