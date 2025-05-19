package com.jamersc.springboot.demo.service;

import com.jamersc.springboot.demo.model.Gender;
import com.jamersc.springboot.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;
    @Test
    void checkIfStudentEmailExist() {
        // given
        String mail = "john@mail.com"; // extract method
        Student student = new Student(
                "John",
                mail,
                Gender.MALE
        );
        underTest.save(student);

        // when
        boolean expected = underTest.selectEmailExist(mail);
        
        // then
        assertThat(expected).isTrue();
    }
}