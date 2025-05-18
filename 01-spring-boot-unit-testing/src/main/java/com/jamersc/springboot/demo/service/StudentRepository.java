package com.jamersc.springboot.demo.service;

import com.jamersc.springboot.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN" +
           " TRUE ELSE FALSE END " +
           " FROM Student s" +
           " WHERE s.email = ?1")
    Boolean selectEmailExist(String email);
}
