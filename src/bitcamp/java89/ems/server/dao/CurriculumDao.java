package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.vo.Contact;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumDao {
  static CurriculumDao obj;
  private String filename = "curriculum-v1.8.data";
  private ArrayList<Curriculum> list;

  public static CurriculumDao getInstance() {
    if (obj == null) {
      obj = new CurriculumDao();
    }
    return obj;
  }
  private CurriculumDao() {
    this.load();
  }
  
  @SuppressWarnings("unchecked")
  private void load() {
    try (
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filename));) {

      list = (ArrayList<Curriculum>)in.readObject();
      
    } catch(EOFException e) {
    } catch (Exception e) {
      System.out.println("강좌 데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    }
  }
  
  public void save() throws Exception {
    try (
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filename));) {
    
      out.writeObject(list);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public ArrayList<Curriculum> getList() {
    return this.list;
  }
  
  public ArrayList<Curriculum> getByName(String curriculumName) {
    ArrayList<Curriculum> searchList = new ArrayList<>();
    
    for (Curriculum curriculum : list) {
      if (curriculum.getCurriculumName().toLowerCase().equals(curriculumName.toLowerCase())) {
        searchList.add(curriculum);
      }
    }
    return searchList;
  }
  
  synchronized public void insert(Curriculum curriculum) {
    list.add(curriculum);
    try {this.save();} catch (Exception e) {}
  }
  
  synchronized public void update(Curriculum curriculum) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getCurriculumName().equals(curriculum.getCurriculumName())) {
        list.set(i, curriculum);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  synchronized public void delete(String curriculumName) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getCurriculumName().equals(curriculumName)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {}
        return;
      }
    }
  }
  
  public boolean existCurriculumName(String curriculumName) {
    for (Curriculum curriculum : list) {
      if (curriculum.getCurriculumName().toLowerCase().equals(curriculumName.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
 
}