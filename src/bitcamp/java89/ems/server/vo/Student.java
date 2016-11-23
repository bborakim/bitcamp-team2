package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Student implements Serializable {
  
  private static final long serialVersionUID = 1L;
  // 인스턴스 변수
  private String userId;
  private String password;
  private String name;
  private String email;
  private String tel;
  private boolean working;
  private int birthYear;
  private String school;

  public Student() {}

  public Student(String userId, String password, String name, String tel) {
    this.userId = userId;
    this.password = password;
    this.name = name;
    this.tel = tel;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public boolean isWorking() {
    return working;
  }

  public void setWorking(boolean working) {
    this.working = working;
  }

  public int getBirthYear() {
    return birthYear;
  }

  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  @Override
  public String toString() {
    return "Student [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", tel="
        + tel + ", working=" + working + ", birthYear=" + birthYear + ", school=" + school + "]";
  }
  
  
}
