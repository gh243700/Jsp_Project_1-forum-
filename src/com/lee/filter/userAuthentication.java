package com.lee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class userAuthentication implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    System.out.println(getClass().getName());
    if (httpRequest.getRequestURI().contains("Add_Topic") || httpRequest.getParameter("do") != null){
      HttpSession session = httpRequest.getSession();
      System.out.println("session_id :"+session.getId());
      if (session.getAttribute("userId") == null || session.getAttribute("username") == null) {
        //httpRequest.setAttribute("userAuth", false);
        httpRequest.getRequestDispatcher("/JSP/Login.jsp").forward(httpRequest,servletResponse);
      }
    }
    filterChain.doFilter(httpRequest, servletResponse);
  }

  @Override
  public void destroy() {}
}
