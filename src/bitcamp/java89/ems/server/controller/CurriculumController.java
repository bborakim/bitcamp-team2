package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.CurriculumDao;
import bitcamp.java89.ems.server.vo.Curriculum;

public class CurriculumController {
  private Scanner in;
  private PrintStream out;
  
  private CurriculumDao curriculumDao;


  public CurriculumController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;
    
    curriculumDao = CurriculumDao.getInstance();
  }
  
  public void save() throws Exception {
    if (curriculumDao.isChanged()) {
      curriculumDao.save();
    }
  }
  
  public boolean service(){
    while (true){
      out.println("강좌관리>");
      out.println();
      String[] commands = in.nextLine().split("\\?");

      try{
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main": return true;
        case "quit": return false;
        default:
          out.println("지원하지않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        out.println("실행 중 오류가 발생했습니다.");
        e.printStackTrace();
      }
    }
  }
  
  private void doList() {
    ArrayList<Curriculum> list = curriculumDao.getList();
    for(Curriculum curriculum : list) {
      out.printf("%s, %s, %s, %s, %s\n",
        curriculum.getCurriculumName(),
        curriculum.getIntroduce(),
        curriculum.getBenefit(),
        curriculum.getTarget(),
        curriculum.getDocument());
        //((curriculum.isLevelTest())? "y" : "n"),
        //curriculum.getLimit(),
        //curriculum.getTime(),
        //curriculum.getTerm());
    }
  }

  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    if (!curriculumDao.existCurriculumName(paramMap.get("name"))) {
      out.println("강좌명을 찾지 못했습니다.");
      return;
    }
    Curriculum curriculum = new Curriculum();
    curriculum.setCurriculumName(paramMap.get("name"));
    curriculum.setIntroduce(paramMap.get("introduce"));
    curriculum.setBenefit(paramMap.get("benefit"));
    curriculum.setTarget(paramMap.get("targe"));
    curriculum.setDocument(paramMap.get("document"));
    
    curriculumDao.update(curriculum);
    out.println("변경 하였습니다.");
  }
  
  private void doAdd(String params){
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    
    if (curriculumDao.existCurriculumName(paramMap.get("name"))) {
      out.println("같은 강좌가 존재합니다. 등록을 취소합니다.");
      return;
    }
    
    Curriculum curriculum = new Curriculum();
    curriculum.setCurriculumName(paramMap.get("name"));
    curriculum.setIntroduce(paramMap.get("introduce"));
    curriculum.setBenefit(paramMap.get("benefit"));
    curriculum.setTarget(paramMap.get("targe"));
    curriculum.setDocument(paramMap.get("document"));
    //curriculum.setLevelTest((paramMap.isLeveltest) ? true : false));
    //curriculum.setLimit(Integer.parseInt(paramMap.get("name")));
   // curriculum.setTime(Integer.parseInt(paramMap.get("name")));
    //curriculum.setTerm(Integer.parseInt(paramMap.get("name")));
    
    curriculumDao.insert(curriculum);
    out.println("등록하였습니다.");
  }
  
  private void doView(String params){
    String[] values = params.split("=");
    
    ArrayList<Curriculum> list = curriculumDao.getByName(values[1]);
    for (Curriculum curriculum : list) {
      out.println("---------------------------");
      out.printf("강좌소개: %s\n", curriculum.getIntroduce());
      out.printf("강좌특전: %s\n", curriculum.getBenefit());
      out.printf("강좌대상: %s\n", curriculum.getTarget());
      out.printf("강좌준비성류: %s\n", curriculum.getDocument());
      //out.printf("강좌레벨테스트: %s\n", ((curriculum.isLevelTest())? "y" : "n"));
      //out.printf("강좌제한인원: %d\n", curriculum.getLimit());
      //out.printf("강좌시간: %d\n", curriculum.getTime());
     // out.printf("강좌기간: %d\n", curriculum.getTerm());
    }
  }
  
  private void doDelete(String params) {
    String[] values = params.split("=");
    
    if (!curriculumDao.existCurriculumName(values[1])) {
      out.println("해당 강좌명이 없습니다.");
      return;
    }
    curriculumDao.delete(values[1]);
    out.println("해당 강좌명을 삭제 완료하였습니다.");
  }
}