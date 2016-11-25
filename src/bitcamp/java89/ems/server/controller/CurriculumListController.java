package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumListController implements Command {
 
  public void service(HashMap<String,String> paramMap, PrintStream out){
    try {
      CurriculumDao curriculumDao = CurriculumDao.getInstance();
    ArrayList<Curriculum> list = curriculumDao.getList();
    for(Curriculum curriculum : list) {
      out.printf("%s, %s, %s, %s, %s\n",
        curriculum.getCurriculumName(),
        curriculum.getIntroduce(),
        curriculum.getBenefit(),
        curriculum.getTarget(),
        curriculum.getDocument());
    }
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}