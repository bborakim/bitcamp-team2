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

public class CurriculumDao extends AbstractDao<Curriculum>{
  static CurriculumDao obj;

  public static CurriculumDao getInstance() throws Exception {
    if (obj == null) {
      obj = new CurriculumDao();
      obj.load();
    }
    return obj;
  }
  
  private CurriculumDao() throws Exception {
    super("contact -v1.9.data");
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