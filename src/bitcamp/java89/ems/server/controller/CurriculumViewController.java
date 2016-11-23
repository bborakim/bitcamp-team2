package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumViewController implements Command {
  private CurriculumDao curriculumDao;
  


  public CurriculumViewController() {
    curriculumDao = CurriculumDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out){
    ArrayList<Curriculum> list = curriculumDao.getByName(paramMap.get("name"));
    for (Curriculum curriculum : list) {
      out.println("---------------------------");
      out.printf("강좌소개: %s\n", curriculum.getIntroduce());
      out.printf("강좌특전: %s\n", curriculum.getBenefit());
      out.printf("강좌대상: %s\n", curriculum.getTarget());
      out.printf("강좌준비성류: %s\n", curriculum.getDocument());
    }
  }
}