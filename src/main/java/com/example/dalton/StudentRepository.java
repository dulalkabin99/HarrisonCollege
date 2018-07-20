package com.example.dalton;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{
    Iterable <Student> findByUserNameContainingIgnoreCaseAndPassword(String username, String password);
    long countByUserNameAndPassword(String username, String password);
  Iterable<Class> findByUserName(String student);
  /*Iterable<Student>findById(long id);*/
  Iterable<Student> findStudentById(long id);
}
