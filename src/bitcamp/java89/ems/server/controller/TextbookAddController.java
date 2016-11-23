package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookAddController implements Command {
  private TextbookDao textbookDao;
  

  public TextbookAddController() {
    textbookDao = TextbookDao.getInstance();
  }

  public void service(HashMap<String,String> paramMap, PrintStream out) {
    if (textbookDao.existTitle(paramMap.get("title"))) {
      out.println("이메일이 이미 존재합니다. 등록을 취소합니다.");
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
    
    textbookDao.insert(book);
    out.println("등록하였습니다.");
  }
}
//textbook/add?title=java programming&author=james&press=비트출판사&price=30000&pages=200&stock=2&className=java89&suppl=y&distr=y
