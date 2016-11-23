package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;

public class StudentDeleteController implements Command {
  private StudentDao studentDao;


  public StudentDeleteController() {
    studentDao = StudentDao.getInstance();
  }

  // delete?id=aaa
  public void service(HashMap<String,String> paramMap, PrintStream out) {

    if (!studentDao.existUserId(paramMap.get("id"))) {
      out.println("아이디가 존재합니다. 등록을 취소합니다.");
      return;
    }

    studentDao.delete(paramMap.get("id"));
    out.println("삭제완료");
  }
}
