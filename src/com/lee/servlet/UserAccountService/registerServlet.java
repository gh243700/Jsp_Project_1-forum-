package com.lee.servlet.UserAccountService;

import com.lee.beans.User;
import com.lee.dao.UserDao;
import com.lee.util.DriverConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class registerServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("in :" + getClass());
    req.getRequestDispatcher("/JSP/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("in :" + getClass());
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String firstname = req.getParameter("firstname");
    String lastname = req.getParameter("lastname");
    String password = req.getParameter("password");
    String age = req.getParameter("age");

    Date date = new Date();
    date.setTime(date.getTime());

    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setFirst_name(firstname);
    user.setLast_name(lastname);
    user.setPassword(password);
    user.setAge(Integer.parseInt(age));
    user.setCreated_on(date);
    int rowsAffected = 0;
    Connection connection = null;
    try {
      connection = new DriverConnectionManager().getConnection();
      UserDao dao = new UserDao(connection);
      boolean checkuserName = dao.CheckifExists(username, "username");
      boolean checkuserEmail = dao.CheckifExists(email, "email");
      if (!checkuserEmail || !checkuserName) {
        if (!checkuserEmail) {
          String checkEmailMessage = "email already exists";
          req.setAttribute("checkEmailMessage", checkEmailMessage);
        }
        if (!checkuserName) {
          String checkUsernameMessage = "username already exists";
          req.setAttribute("checkUsernameMessage", checkUsernameMessage);
        }
        HashMap<String, Object> values = new HashMap<>();
        values.put("username", username);
        values.put("email", email);
        values.put("firstname", firstname);
        values.put("lastname", lastname);
        values.put("password", password);
        values.put("age", age);
        req.setAttribute("data", values);
        doGet(req, resp);
      } else {
        try {
          rowsAffected = dao.create(user);
        } catch (RuntimeException e) {
          e.printStackTrace();
        }
        System.out.println("rowsAffected" + rowsAffected);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String message = "";
    if (rowsAffected != 1) {
      message = "F";
    } else {
      message = "success";
      resp.sendRedirect(getServletContext().getInitParameter("uri") + "/home");
    }
  }

  //  private <T> boolean checkIfExists(T val, String col, Connection connection) {
  //    UserDao userDao = new UserDao(connection);
  //    return null
  //  }
}
