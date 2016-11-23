/* 작업내용 : 직렬화 적용
 */
package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.TextbookDao;
import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookController {
  private Scanner in;
  private PrintStream out;
  private ArrayList<Textbook> list;
  String filePath = "Textbook_v1.6.data";
  private TextbookDao textbookDao;

  public TextbookController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;
   
    textbookDao = TextbookDao.getInstance();
  }

  public boolean service() {
    while(true) {
      out.println("교재관리> ");
      out.println();
      String command[] = in.nextLine().toLowerCase().split("\\?");
      try {
        switch (command[0]) {
        case "add": this.doAdd(command[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(command[1]); break;
        case "delete": this.doDelete(command[1]); break;
        case "update" : this.doUpdate(command[1]); break;
        case "main": return true;
        case "quit": return false;
        default:
          System.out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("인덱스가 유효하지 않습니다");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("실행 중 오류가 발생했습니다.");
      }
    }//while
  }
  
  // 아래 doXXX() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  //add?title=javaprogramming&author=aaa&press=bitpress&price=30000&pages=200&stock=3&className=bit89&suppl=y&distr=n
  //add?title=db study&author=bbb&press=bitpress&price=10000&pages=100&stock=1&className=bit89&suppl=y&distr=n
  private void doAdd(String params) {
    System.out.println(params);
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
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

  private void doList() {
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

  private void doView(String params) {
    System.out.println(params);
    String[] values = params.split("=");

    for (Textbook book : list) {
      if (book.getTitle().equals(values[1])) {
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
        out.println("-----------------------");
        return;
      }
    }
    out.println("해당 이름의 교재가 없습니다.");
  }

  private void doDelete(String params) {
    System.out.println(params);
    String deleteTitle = (params.split("="))[1];
    
    if (!textbookDao.existTitle(deleteTitle)) {
      out.println("해당 이름의 교재가 없습니다.");
      return;
    }
    
    textbookDao.delete(deleteTitle);
    
    out.println("삭제하였습니다.");
  }

  //update?title=db study&author=ccc&press=bitpress&price=10000&pages=400&stock=4&className=bit89&suppl=y&distr=n
  private void doUpdate(String params) {
    System.out.println(params);
    String[] values = params.split("&");
    HashMap<String, String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (textbookDao.existTitle(paramMap.get("title"))) {
      out.println("해당 이름의 교재가 없습니다.");
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
