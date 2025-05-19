package com.jamersc.springboot.demo.service;

import com.jamersc.springboot.demo.model.Gender;
import com.jamersc.springboot.demo.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

     // Testing Custom Repositories

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll(); // after each test remove all created, clean state after
    }

    @Test
    void itShouldCheckWhenStudentEmailExist() {
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

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExist() {
        // given
        String mail = "john@mail.com";

        // when
        boolean expected = underTest.selectEmailExist(mail);

        // then
        assertThat(expected).isFalse();
    }
}