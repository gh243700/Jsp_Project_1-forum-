package com.lee.servlet.UserAccountService;

import com.lee.dao.UserDao;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    UserDao userDao = null;
    try {
      connection = new DriverConnectionManager().getConnection();
      userDao = new UserDao(connection);
      boolean checkIfUserExists = userDao.checkifvaliduser(name, password);
      System.out.println(checkIfUserExists);
      if (checkIfUserExists) {
        HttpSession httpSession = req.getSession();
        int userid = userDao.getIdByusernameOrEmail(name);
        String username = userDao.readById(userid).getUsername();
        httpSession.setAttribute("userId", userid);
        httpSession.setAttribute("username", username);
        System.out.println(userid);
        resp.sendRedirect(getServletContext().getInitParameter("uri")+"/main");
      }else{
        throw new RuntimeException();
      }
    }
    catch (NullPointerException e) {
      throw new RuntimeException("cant find user");
    }
    catch (SQLException | RuntimeException e) {
      req.setAttribute("message", e.getMessage());
      req.getRequestDispatcher("/JSP/ErrorPage.jsp").forward(req, resp);
    }
  }
}
