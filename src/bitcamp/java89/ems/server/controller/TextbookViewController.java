package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookViewController implements Command {
  public void service(HashMap<String,String> paramMap, PrintStream out) {
    try {
      TextbookDao textbookDao = TextbookDao.getInstance();
      ArrayList<Textbook> list = textbookDao.getListByTitle(paramMap.get("title"));
      for (Textbook book : list) {
          out.println("-----------------------");
          out.println("교재명: " + book.getTitle());
          out.println("저자: " + book.getAuthor());
          out.println("출판사: " + book.getPress());
          out.println("가격: " + book.getPrice());
          out.println("쪽수: " + book.getPages());
          out.println("재고수량: " + book.getStock());
          out.println("강좌명: " + book.getClassName());
          out.println("부록: " + (book.isSuppl() ? "부록있음" : "부록없음"));
          out.println("배부: " + (book.isDistr() ? "배부함" : "배부안함"));
      } 
    }catch (Exception e) {
      out.println("[Textbook] 작업 중 예외가 발생하였습니다.");
      e.printStackTrace();
    }
  }
}
