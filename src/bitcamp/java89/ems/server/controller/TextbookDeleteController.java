package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;

public class TextbookDeleteController implements Command {
  private TextbookDao textbookDao;
  

  public TextbookDeleteController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!textbookDao.existTitle(paramMap.get("title"))) {
      out.println("입력하신 교재가 없습니다.");
      return;
    }
    
    textbookDao.delete(paramMap.get("title"));
    out.println("입력하신 교재를 삭제하였습니다.");
  }
}
