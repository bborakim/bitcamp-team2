package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentUpdateController implements Command {
  private StudentDao studentDao;


  public StudentUpdateController() {
    studentDao = StudentDao.getInstance();
  }

  // delete?id=aaa
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!studentDao.existUserId(paramMap.get("id"))) {
      out.print("이메일을 찾지 못했습니다.");
      return;
    }
    
    Student student = new Student();    
    
    student.setUserId(paramMap.get("id"));
    student.setPassword(paramMap.get("pw"));
    student.setName(paramMap.get("name"));
    student.setTel(paramMap.get("tel"));
    student.setWorking(paramMap.get("work").equals("y")? true : false);
    student.setBirthYear(Integer.parseInt(paramMap.get("birth")));
    student.setSchool(paramMap.get("school"));
    
    studentDao.update(student);
    out.println("학생 정보를 변경하였습니다.");
  }
}
