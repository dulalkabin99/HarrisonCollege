package com.example.dalton;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{
    Iterable <Student> findByUserNameContainingIgnoreCaseAndPassword(String username, String password);
    long countByUserNameAndPassword(String username, String password);
}
