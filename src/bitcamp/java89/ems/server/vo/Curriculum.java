package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

/**
 * @author user_pc
 *
 */
public class Curriculum implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String curriculumName;
  protected String introduce;
  protected String benefit;
  protected String target;
  protected String document;
  protected boolean levelTest;
  protected int limit;
  protected int time;
  protected int term;

  public Curriculum() {}
  



  public Curriculum(String curriculumName, String introduce, String benefit, String target, String document,
      boolean levelTest, int limit, int time, int term) {
    super();
    this.curriculumName = curriculumName;
    this.introduce = introduce;
    this.benefit = benefit;
    this.target = target;
    this.document = document;
    this.levelTest = levelTest;
    this.limit = limit;
    this.time = time;
    this.term = term;
  }

  @Override
  public String toString() {
    return "Curriculum [curriculumName=" + curriculumName + ", introduce=" + introduce + ", benefit=" + benefit
        + ", target=" + target + ", document=" + document + ", levelTest=" + levelTest + ", limit=" + limit + ", time="
        + time + ", term=" + term + "]";
  }






  public Curriculum(String curriculumName, String benefit, String document, int limit, int time) {
    this.curriculumName = curriculumName;
    this.benefit = benefit;
    this.document = document;
    this.limit = limit;
    this.time = time;
  }




  public String getCurriculumName() {
    return curriculumName;
  }




  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }




  public String getIntroduce() {
    return introduce;
  }




  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }




  public String getBenefit() {
    return benefit;
  }




  public void setBenefit(String benefit) {
    this.benefit = benefit;
  }




  public String getTarget() {
    return target;
  }




  public void setTarget(String target) {
    this.target = target;
  }




  public String getDocument() {
    return document;
  }




  public void setDocument(String document) {
    this.document = document;
  }




  public boolean isLevelTest() {
    return levelTest;
  }




  public void setLevelTest(boolean levelTest) {
    this.levelTest = levelTest;
  }




  public int getLimit() {
    return limit;
  }




  public void setLimit(int limit) {
    this.limit = limit;
  }




  public int getTime() {
    return time;
  }




  public void setTime(int time) {
    this.time = time;
  }




  public int getTerm() {
    return term;
  }




  public void setTerm(int term) {
    this.term = term;
  }




  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
  
}
