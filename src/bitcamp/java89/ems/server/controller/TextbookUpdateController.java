package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookUpdateController implements Command {
  private TextbookDao textbookDao;
  

  public TextbookUpdateController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (!textbookDao.existTitle(paramMap.get("title"))) {
      out.println("입력하신 교재가 없습니다.");
      return;
    }
    
    Textbook book = new Textbook();
    book.setTitle(paramMap.get("title"));
    book.setAuthor(paramMap.get("author"));
    book.setPress(paramMap.get("press"));
    book.setPrice(Integer.parseInt(paramMap.get("price")));
    book.setPages(Integer.parseInt(paramMap.get("pages")));
    book.setStock(Integer.parseInt(paramMap.get("stock")));
    book.setClassName(paramMap.get("className"));
    book.setSuppl(paramMap.get("suppl").equals("y")? true : false);
    book.setDistr(paramMap.get("distr").equals("y")? true : false);

    textbookDao.update(book);
    
    out.println("변경하였습니다.");
  }
}
