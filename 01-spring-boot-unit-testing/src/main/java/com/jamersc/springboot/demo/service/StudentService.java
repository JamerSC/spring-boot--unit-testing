package com.jamersc.springboot.demo.service;

import com.jamersc.springboot.demo.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    Student getStudentById(Long studentId);
    void addStudent(Student student);
    void deleteStudent(Long studentId);
}
