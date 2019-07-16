package com.lee.filter;

import com.lee.beans.TopicReply;
import com.lee.dao.TopicDAO;
import com.lee.dao.TopicReplyDAO;
import com.lee.util.DriverConnectionManager;
import com.sun.deploy.net.HttpRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TopicFilter implements Filter {

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
    if (httpRequest.getMethod().equals("GET")) {

      System.out.println("----------------GET-------------"+getClass().getName());
      String[] attr = httpRequest.getParameter("attr").split("-");

      int topic_id = Integer.parseInt(attr[0]);
      System.out.println(topic_id);
      TopicDAO topicDAO = null;
      TopicReplyDAO topicReplyDAO = null;
      RequestDispatcher requestDispatcher_Error = httpRequest.getRequestDispatcher("/JSP/ErrorPage.jsp");

      try {
        Connection connection = new DriverConnectionManager().getConnection();
        topicDAO = new TopicDAO(connection);
        topicReplyDAO = new TopicReplyDAO(connection);
      } catch (SQLException e) {
        requestDispatcher_Error.forward(httpRequest, servletResponse);
      }
      httpRequest.setAttribute("topic_id",topic_id);
      httpRequest.setAttribute("topicDAO",topicDAO);
      httpRequest.setAttribute("topicReplyDAO",topicReplyDAO);
    }
    if (httpRequest.getMethod().equals("POST")) {
      System.out.println("----------------POST-------------"+getClass().getName());

    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    System.out.println(getClass().getName() + "->destroy()");
  }
}
