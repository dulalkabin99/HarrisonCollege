package com.example.dalton;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String majorName;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Course> courses;

    @OneToMany(mappedBy = "major", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Student> students;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
