package com.example.dalton;

import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Long>{
    Iterable <Instructor> findAllByUserNameContainingIgnoreCaseAndPassword(String username, String password);

    long countByUserNameAndPassword(String username, String password);
    Instructor findFirstInstructorByUserName(String username);
}
