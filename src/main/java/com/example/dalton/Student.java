package com.example.dalton;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String studentName;
    private String yearEntry;

    private String studentNum;
    private String userName;
    private String password;

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

    @ManyToMany
    @JoinTable(
            name ="STUDENT_CLASS", joinColumns=@JoinColumn(name="STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CLASS_ID", referencedColumnName = "ID"))
    private Set<Class> classes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private Major major;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "advisor_id")
  private Advisor advisor;


    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Grade> grades;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getYearEntry() {
        return yearEntry;
    }

    public void setYearEntry(String yearEntry) {
        this.yearEntry = yearEntry;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(long num) {
        this.studentNum = "st- "+ num;
    }

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

  public Advisor getAdvisor() {
    return advisor;
  }

  public void setAdvisor(Advisor advisor) {
    this.advisor = advisor;
  }
}
