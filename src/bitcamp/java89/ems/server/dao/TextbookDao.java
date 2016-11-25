package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookDao extends AbstractDao<Textbook> {
  static TextbookDao obj;
  
  public static TextbookDao getInstance() throws Exception {
    if (obj == null) {
      obj = new TextbookDao();
      obj.load();
    }
    return obj;
  }

  private TextbookDao() throws Exception {
    super("Textbook-v1.9.data");
  }
  
  public ArrayList<Textbook> getList() {
    return this.list;
  }
  
  public ArrayList<Textbook> getListByTitle(String name) {
    ArrayList<Textbook> results = new ArrayList<>();
    
    for (Textbook textbook : list) {
      if (textbook.getTitle().equals(name)) {
        results.add(textbook);
      }
    }
    return results;
  }
  
  synchronized public void insert(Textbook textbook) {
    list.add(textbook);
    try {this.save();} catch (Exception e) {}
  }
  
  public boolean existTitle(String title) {
    for (Textbook textbook : list) {
      if (textbook.getTitle().toLowerCase().equals(title.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
  
  synchronized public void delete(String title) {  
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getTitle().equals(title)) {
        list.remove(i);
        try {save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  synchronized public void update(Textbook book) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getTitle().equals(book.getTitle())) {
        list.set(i, book);
        try {save();} catch (Exception e) {}
        return;
      }
    }
  }
}


