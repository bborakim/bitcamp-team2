package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumDeleteController implements Command {
  private CurriculumDao curriculumDao;
  


  public CurriculumDeleteController() {
    curriculumDao = CurriculumDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out){
    if (!curriculumDao.existCurriculumName(paramMap.get("name"))) {
      out.println("해당 강좌명이 없습니다.");
      return;
    }
    curriculumDao.delete(paramMap.get("name"));
    out.println("해당 강좌명을 삭제 완료하였습니다.");
  }
}