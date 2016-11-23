package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.controller.ContactAddController;
import bitcamp.java89.ems.server.controller.ContactDeleteController;
import bitcamp.java89.ems.server.controller.ContactListController;
import bitcamp.java89.ems.server.controller.ContactUpdateController;
import bitcamp.java89.ems.server.controller.ContactViewController;
<<<<<<< HEAD
import bitcamp.java89.ems.server.controller.StudentAddController;
import bitcamp.java89.ems.server.controller.StudentDeleteController;
import bitcamp.java89.ems.server.controller.StudentListController;
import bitcamp.java89.ems.server.controller.StudentUpdateController;
import bitcamp.java89.ems.server.controller.StudentViewController;
=======
import bitcamp.java89.ems.server.controller.CurriculumAddController;
import bitcamp.java89.ems.server.controller.CurriculumDeleteController;
import bitcamp.java89.ems.server.controller.CurriculumListController;
import bitcamp.java89.ems.server.controller.CurriculumUpdateController;
import bitcamp.java89.ems.server.controller.CurriculumViewController;
>>>>>>> db65a5a3ae6eea1249def16a94e2dc4a80d7355f

public class EduAppServer {
  HashMap<String,Command> commandMap = new HashMap<>();
  
  public EduAppServer() {
    commandMap.put("contact/list", new ContactListController());
    commandMap.put("contact/view", new ContactViewController());
    commandMap.put("contact/add", new ContactAddController());
    commandMap.put("contact/delete", new ContactDeleteController());
    commandMap.put("contact/update", new ContactUpdateController());
<<<<<<< HEAD
    commandMap.put("student/list", new StudentListController());
    commandMap.put("student/view", new StudentViewController());
    commandMap.put("student/add", new StudentAddController());
    commandMap.put("student/delete", new StudentDeleteController());
    commandMap.put("student/update", new StudentUpdateController());
=======
    commandMap.put("curriculum/list", new CurriculumListController());
    commandMap.put("curriculum/view", new CurriculumViewController());
    commandMap.put("curriculum/add", new CurriculumAddController());
    commandMap.put("curriculum/delete", new CurriculumDeleteController());
    commandMap.put("curriculum/update", new CurriculumUpdateController());
    
>>>>>>> db65a5a3ae6eea1249def16a94e2dc4a80d7355f
  }
  
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버실행중................");
    
    while (true) {
    new RequestThread(ss.accept(), commandMap).start();
    }
    //ss.close();
  }
  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
}