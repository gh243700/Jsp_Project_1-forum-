package com.lee;

import com.lee.beans.Forum;
import com.lee.beans.Parent_Forum;
import com.lee.beans.Topic;
import com.lee.dao.ForumDao;
import com.lee.dao.Parent_forumDAO;
import com.lee.dao.TopicDAO;
import com.lee.util.DriverConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TooManyListenersException;

public class testMain {
  public static void main(String[] args) throws SQLException {
    DriverConnectionManager dcm = new DriverConnectionManager();


    Forum forum = new ForumDao(dcm.getConnection()).readById(1);
    System.out.println(forum.getDescription());


//    String srt = "DIs-ddd";
//     System.out.println(srt.replace("-","*"));
//    Parent_forumDAO parent_forumDAO = new Parent_forumDAO(new DriverConnectionManager().getConnection());
//    List<Parent_Forum> parent_forums = parent_forumDAO.readAll();
//    for (Parent_Forum obj: parent_forums){
//      System.out.println(obj);
//    }
//
//    System.out.println("--------------------------------------------------");
//
//    ForumDao forumDAO =
//        new ForumDao(new DriverConnectionManager().getConnection());
//   List<Forum> list = forumDAO.readAll();
//   for (Forum obj: list) {
//       System.out.println(obj);
//   }
  }
}
