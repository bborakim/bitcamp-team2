package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumUpdateController implements Command {

  public void service(HashMap<String,String> paramMap, PrintStream out){
    try {
      CurriculumDao curriculumDao = CurriculumDao.getInstance();
      if (!curriculumDao.existCurriculumName(paramMap.get("name"))) {
        out.println("강좌명을 찾지 못했습니다.");
        return;
      }
      Curriculum curriculum = new Curriculum();
      curriculum.setCurriculumName(paramMap.get("name"));
      curriculum.setIntroduce(paramMap.get("introduce"));
      curriculum.setBenefit(paramMap.get("benefit"));
      curriculum.setTarget(paramMap.get("targe"));
      curriculum.setDocument(paramMap.get("document"));
      
      curriculumDao.update(curriculum);
      out.println("변경 하였습니다.");
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}