package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookListController implements Command {
  private TextbookDao textbookDao;
  

  public TextbookListController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    ArrayList<Textbook> list = textbookDao.getList();
    for (Textbook textbook : list) {
      out.printf("%s, %s, %s, %d원, %d쪽, %d남음, %s,"
          + " %s, %s",
          textbook.getTitle(),
          textbook.getAuthor(),
          textbook.getPress(),
          textbook.getPrice(),
          textbook.getPages(),
          textbook.getStock(),
          (textbook.isSuppl() ? "부록있음" : "부록없음"),
          textbook.getClassName(),
          (textbook.isDistr() ? "배부함" : "배부안함"));
    }
  }
}
