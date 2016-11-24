package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Textbook;

public class TextbookDao {
  static TextbookDao obj;
  private String filename = "Textbook_v1.8.data";
  private ArrayList<Textbook> list;
  
  public static TextbookDao getInstance() {
    if (obj == null) {
      obj = new TextbookDao();
    }
    return obj;
  }

  private TextbookDao() {
    this.load();
  }
  
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);
      
      list = (ArrayList<Textbook>)in.readObject();
  
      
      // 파일 생성 후 데이터 없음 or 파일을 모두 읽음
    
    } catch (EOFException e) {
    } catch (Exception e) {
      System.out.println("데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {}
    }
  }
  
  public void save() throws Exception {
      FileOutputStream out0 = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(out0);

      out.writeObject(list);
      
      out.close();
      out0.close();
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


