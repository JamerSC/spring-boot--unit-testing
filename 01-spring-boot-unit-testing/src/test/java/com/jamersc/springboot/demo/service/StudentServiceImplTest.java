package com.jamersc.springboot.demo.service;

import com.jamersc.springboot.demo.exception.BadRequestException;
import com.jamersc.springboot.demo.model.Gender;
import com.jamersc.springboot.demo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given; // I added this manually
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock private StudentRepository studentRepository; // Mocks the StudentRepository using Mockito.
    private StudentServiceImpl underTest;  // Initializes a new StudentServiceImpl before each test, injecting the mocked repository.
    // This ensures each test gets a fresh, isolated service instance with a mock dependency.

    @BeforeEach // run before each test
    void setUp() {
        underTest = new StudentServiceImpl(studentRepository); // fresh instance in student service
    }

    @Test
    void canGetAllStudents() {
        // when
        underTest.getAllStudents(); // the actual method call on the service.

        // then
        verify(studentRepository).findAll(); // ensure the service calls findAll() on the repository

        // ✅ Passes if findAll() was called once.
        // ❌ Fails if it wasn’t called, was called multiple times, or some other method was called.
    }

    @Test
    @Disabled
    void getStudentById() {
    }

    @Test
    void canAddStudent() {
        // given
        String mail = "john@mail.com";
        Student student = new Student( // A sample Student object is created.
                "John",
                mail,
                Gender.MALE
        );

        // when
        underTest.addStudent(student); // is called on the service.

        // then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        // ArgumentCaptor<Student> is used to capture the exact Student object
        // passed to studentRepository.save(...).

        verify(studentRepository).save(studentArgumentCaptor.capture());
        // verify(studentRepository).save(...) ensures the method was called.

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student); // confirms that the correct student instance was saved.
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        Student student = new Student( // A sample Student object is created.
                "John",
                "john@mail.com",
                Gender.MALE
        );

        given(studentRepository.selectEmailExist(student.getEmail()))
                .willReturn(true);

        // when

        // then - used lambda
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " already exists!");

    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}