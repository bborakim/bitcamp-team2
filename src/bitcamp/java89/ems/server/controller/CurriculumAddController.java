package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumAddController implements Command {

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      CurriculumDao curriculumDao = CurriculumDao.getInstance();
      if (curriculumDao.existCurriculumName(paramMap.get("name"))) {
        out.println("같은 강좌가 존재합니다. 등록을 취소합니다.");
        return;
      }
      
      Curriculum curriculum = new Curriculum();
      curriculum.setCurriculumName(paramMap.get("name"));
      curriculum.setIntroduce(paramMap.get("introduce"));
      curriculum.setBenefit(paramMap.get("benefit"));
      curriculum.setTarget(paramMap.get("target"));
      curriculum.setDocument(paramMap.get("document"));
      
      curriculumDao.insert(curriculum);
      out.println("등록하였습니다.");
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}