package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumDeleteController implements Command {

  public void service(HashMap<String,String> paramMap, PrintStream out){
    try {
      CurriculumDao curriculumDao = CurriculumDao.getInstance();
      if (!curriculumDao.existCurriculumName(paramMap.get("name"))) {
        out.println("해당 강좌명이 없습니다.");
        return;
      }
      curriculumDao.delete(paramMap.get("name"));
      out.println("해당 강좌명을 삭제 완료하였습니다.");
    } catch (Exception e) {
      out.println("작업중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}