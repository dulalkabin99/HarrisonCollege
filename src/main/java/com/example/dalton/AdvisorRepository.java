package com.example.dalton;

import org.springframework.data.repository.CrudRepository;

public interface AdvisorRepository extends CrudRepository<Advisor, Long>{
  Iterable <Advisor> findAllByUserNameContainingIgnoreCaseAndPassword(String username, String password);

  long countByUserNameAndPassword(String username, String password);
  Advisor findFirstInstructorByUserName(String username);
}
