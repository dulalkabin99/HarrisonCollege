package com.example.dalton;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String grade;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="class_id")
    private Class aClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="student_id")
    private Student student;

}
