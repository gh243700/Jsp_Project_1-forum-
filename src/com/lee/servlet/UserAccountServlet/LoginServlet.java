package com.lee.servlet.UserAccountServlet;

import com.lee.repository.UserRepository;
import com.lee.repository.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      req.getRequestDispatcher("/JSP/Login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("in :" + getClass());
    String name = req.getParameter("nameOrEmail");
    String password = req.getParameter("password");
    String remember = req.getParameter("rememberUser");

    Connection connection = null;
    UserRepository userRepository = null;
    try {
      connection = new DriverConnectionManager().getConnection();
      userRepository = new UserRepository(connection);
      boolean checkIfUserExists = userRepository.checkifvaliduser(name, password);
      System.out.println(checkIfUserExists);
      if (checkIfUserExists) {
        HttpSession httpSession = req.getSession();
        int userid = userRepository.getIdByusernameOrEmail(name);
        String username = userRepository.readById(userid).getUsername();
        httpSession.setAttribute("userId", userid);
        httpSession.setAttribute("username", username);
        System.out.println(userid);
        resp.sendRedirect(getServletContext().getInitParameter("uri")+"/main");
      }else{
        doGet(req,resp);
      }
    }
    catch (Exception e) {
      doGet(req,resp);
    }
  }
}
