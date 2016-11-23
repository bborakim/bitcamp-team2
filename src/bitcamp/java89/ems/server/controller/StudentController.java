package bitcamp.java89.ems.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Student;

public class StudentController {
  private Scanner in;
  private PrintStream out;
  private String filename = "C:/Users/BitCamp/git/java89/bitcamp-team1/src/bitcamp/java89/temp/student2.data";
  private ArrayList<Student> list;
  private boolean changed;

  public StudentController(Scanner in, PrintStream out) {    
    list = new ArrayList<Student>();

    this.in = in;
    this.out = out;    
    this.load(); 
  }

  public boolean isChanged() {
    return changed;
  }

  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Student>)in.readObject();

    } catch (EOFException e) {

    } catch (Exception e) {
      out.println("학생 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {

      }
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);

    changed = false;

    out.close();
    out0.close();
  }

  public boolean service() {    
    while (true) {
      out.println("학생관리> ");
      out.println();
      String[] commands = in.nextLine().split("\\?");

      try {
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main":
          return true;
        case "quit" :
          return false;
        default:
          out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
        e.printStackTrace();
      } 
    }   
  }

  private void doList() {
    for (Student student : list) {
      out.printf("%s,%s,%s,%s,%s,%s,%d,%s\n",
          student.getUserId(),
          student.getPassword(),
          student.getName(),
          student.getTel(),
          student.getEmail(),
          ((student.isWorking())?"yes":"no"),
          student.getBirthYear(),
          student.getSchool());
    }
  }
  // UserId
  private void doUpdate(String params) {

    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }

    for (Student student : list) {
      if (!student.getUserId().equals(paramMap.get("userId"))) {
        continue;
      }
      student.setPassword(paramMap.get("password"));
      student.setName(paramMap.get("name"));
      student.setTel(paramMap.get("tel"));
      student.setEmail(paramMap.get("email"));
      student.setWorking(paramMap.get("working").equals("y") ? true : false);
      student.setBirthYear(Integer.parseInt(paramMap.get("birthYear")));
      student.setSchool(paramMap.get("school"));

      changed = true;
      out.println("학생 정보를 변경하였습니다.");
      return;
    }
    out.println("해당 아이디의 학생이 없습니다.");
  }

  private void doAdd(String params) {

    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();

    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }

    Student student = new Student();
    student.setUserId(paramMap.get("userId"));
    student.setPassword(paramMap.get("password"));
    student.setName(paramMap.get("name"));
    student.setTel(paramMap.get("tel"));
    student.setEmail(paramMap.get("email"));
    student.setWorking(paramMap.get("working").equals("y") ? true : false);
    student.setBirthYear(Integer.parseInt(paramMap.get("birthYear")));
    student.setSchool(paramMap.get("school"));

    list.add(student);
    changed = true;

  }
  // ID
  private void doView(String params) {
    String[] kv = params.split("=");

    for (Student student : list) {
      if (!student.getUserId().equals(kv[1])) {
        continue;
      }
      out.printf("아이디: %s\n", student.getUserId());
      out.printf("암호: (***)\n");
      out.printf("이름: %s\n", student.getName());
      out.printf("전화: %s\n", student.getTel());
      out.printf("이메일: %s\n", student.getEmail());
      out.printf("재직중: %s\n", (student.isWorking()) ? "Yes" : "No");
      out.printf("태어난 해: %d\n", student.getBirthYear());
      out.printf("학교: %s\n", student.getSchool());
      return;
    }
    out.println("해당 아이디의 학생이 없습니다.");

  }
  // ID
  private void doDelete(String params) {
    String[] kv = params.split("=");

    for (Student student : list) {
      if (!student.getUserId().equals(kv[1])) {
        continue;
      }
      list.remove(student);
      changed = true;
      out.printf("%s 학생 정보를 삭제하였습니다.\n", student.getUserId());
      return;
    }
    out.println("해당 아이디의 학생이 없습니다.");
  }   

}
