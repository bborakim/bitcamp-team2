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
    for (Textbook book : list) {
      out.println(String.format("%s, %s, %s, %d원, %d쪽, %d남음, %s,"
          + " %s, %s",
          book.getTitle(),
          book.getAuthor(),
          book.getPress(),
          book.getPrice(),
          book.getPages(),
          book.getStock(),
          (book.isSuppl() ? "부록있음" : "부록없음"),
          book.getClassName(),
          (book.isDistr() ? "배부함" : "배부안함")));
    }
  }
}
