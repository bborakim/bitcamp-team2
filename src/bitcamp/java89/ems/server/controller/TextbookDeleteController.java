package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;

public class TextbookDeleteController implements Command {
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      TextbookDao textbookDao = TextbookDao.getInstance();
      if (!textbookDao.existTitle(paramMap.get("title"))) {
        out.println("입력하신 교재가 없습니다.");
        return;
      }
      
      textbookDao.delete(paramMap.get("title"));
      out.println("입력하신 교재를 삭제하였습니다.");
    } catch (Exception e) {
      out.println("[Textbook] 작업 중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
