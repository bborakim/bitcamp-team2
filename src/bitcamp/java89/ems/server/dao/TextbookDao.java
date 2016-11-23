package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
//    try {
//      File file = new File(filename);
//      if(!file.exists()) {
//        file.createNewFile();
//      }
//    } catch (IOException e){
//      System.out.println("파일을 정상적으로 로드하지 못했습니다.");
//    }

    FileInputStream fis = null;
    ObjectInputStream in = null;
    try {
      fis = new FileInputStream(this.filename);
      in = new ObjectInputStream(fis);
      
      list = (ArrayList<Textbook>)in.readObject();
    } catch (EOFException e) {
      // 파일 생성 후 데이터 없음 or 파일을 모두 읽음
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    } finally {
      try {
        in.close();
        fis.close();
      } catch (Exception e) {}
    }
  }
  
  public void save() {
    try {
      FileOutputStream fos = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(fos);

      out.writeObject(list);
      
      out.close();
      fos.close();

      System.out.println("교재 정보 저장!");
    } catch (IOException e) {
      System.out.println("데이터 저장 중 오류 발생!");
    }
  }
  
  public ArrayList<Textbook> getList() {
    return this.list;
  }
  
  public ArrayList<Textbook> getListByTitle(String title) {
    ArrayList<Textbook> results = new ArrayList<>();
    for (Textbook book : list) {
      if (book.getTitle().equals(title)) {
        results.add(book);
      }
    }
    return results;
  }
  
  synchronized public void insert(Textbook book) {
    list.add(book);
    try {this.save();} catch (Exception e) {}
  }
  
  public boolean existTitle(String title) {
    for (Textbook book : list) {
      if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
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
        break;
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


