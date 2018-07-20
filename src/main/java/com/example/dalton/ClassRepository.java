package com.example.dalton;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, Long>{
    List<Class> findAllByInstructorId(long id);
    Iterable<Class> findAllByStudents(Student student);
}
