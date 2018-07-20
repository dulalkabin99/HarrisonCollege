package com.example.dalton;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Advisor {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;

    private String advisorName;
    private String employeeNum;

    private String userName;
    private String password;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAdvisorName() {
    return advisorName;
  }

  public void setAdvisorName(String advisorName) {
    this.advisorName = advisorName;
  }

  public String getEmployeeNum() {
    return employeeNum;
  }

  public void setEmployeeNum(String employeeNum) {
    this.employeeNum = employeeNum;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

