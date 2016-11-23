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
  private String filename = "curriculum-v1.7.data";
  private ArrayList<Curriculum> list;
  private boolean changed;

  public static CurriculumDao getInstance() {
    if (obj == null) {
      obj = new CurriculumDao();
    }
    return obj;
  }
  private CurriculumDao() {
    this.load();
  }
 
  public boolean isChanged() {
    return changed;
  }
  
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Curriculum>)in.readObject();
      
    } catch(EOFException e) {
    } catch (Exception e) {
      System.out.println("강좌 데이터 로딩 중 오류 발생!");
      list = new ArrayList<>();
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
      }
    }
  }
  
  public void save() throws Exception{
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);
    
    out.writeObject(list);
    
    changed = false;
    
    out.close();
    out0.close();
  }
  
  public ArrayList<Curriculum> getList() {
    return this.list;
  }
  /*
  public ArrayList<Curriculum> getOne() {
    return this.list;
  }
  */
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
  }
  
  synchronized public void update(Curriculum curriculum) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getCurriculumName().equals(curriculum.getCurriculumName())) {
        list.set(i, curriculum);
        changed = true;
        return;
      }
    }
  }
  synchronized public void delete(String curriculumName) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getCurriculumName().equals(curriculumName)) {
        list.remove(i);
        changed = true;
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