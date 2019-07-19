package com.lee;

import com.lee.model.TopicReply;
import com.lee.repository.TopicReplyRepository;
import com.lee.repository.util.DriverConnectionManager;
import java.sql.SQLException;
import java.util.List;

public class testMain {
  public static void main(String[] args) throws SQLException {
    DriverConnectionManager dcm = new DriverConnectionManager();
    List<TopicReply> list = new TopicReplyRepository(dcm.getConnection()).readAll();
    //    String srt = "DIs-ddd";
    //     System.out.println(srt.replace("-","*"));
    //    Parent_forumDAO parent_forumDAO = new Parent_forumDAO(new
    // DriverConnectionManager().getConnection());
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
