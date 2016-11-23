package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.StudentDao;
import bitcamp.java89.ems.server.vo.Student;

public class StudentAddController implements Command {
  private StudentDao studentDao;


  public StudentAddController() {
    studentDao = StudentDao.getInstance();
  }

  // add?id=aaa&pw=111&name=kim&mail=123@test.com&tel=111-1111&work=y&birth=1991&school=비트
  public void service(HashMap<String,String> paramMap, PrintStream out) {

    if (studentDao.existUserId(paramMap.get("id"))) {
      out.println("아이디가 존재합니다. 등록을 취소합니다.");
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

    studentDao.insert(student);
    out.println("등록완료");
  }
}
