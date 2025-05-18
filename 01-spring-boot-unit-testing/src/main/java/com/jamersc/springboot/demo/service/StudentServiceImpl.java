package com.jamersc.springboot.demo.service;

import com.jamersc.springboot.demo.exception.BadRequestException;
import com.jamersc.springboot.demo.exception.StudentNotFoundException;
import com.jamersc.springboot.demo.model.Student;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
//         return studentRepository.findAll();

        List<Student> studentList = studentRepository.findAll();
        logger.info("List of Students:  {} ", studentList);
        return studentList;
    }

    @Override
    public Student getStudentById(Long studentId) {
//        return studentRepository.findById(studentId)
//                .orElseThrow(() -> new StudentNotFoundException(
//                        "Student ID No. " +  studentId + " is not exist."));

        Student student;
        student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student ID No. " +  studentId + " is not exist."));
        logger.info("Student Id found:  {} ", student);
        return student;
    }

    @Override
    public void addStudent(Student student) {
        Boolean emailExist = studentRepository.selectEmailExist(student.getEmail());
        if (emailExist) {
            throw new BadRequestException(
                    "Email " + student.getEmail() + " already exists!");
        }
        studentRepository.save(student);
        logger.info("Student created:  {} ", student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student ID No. " +  studentId + " is not exist.");
        }
        studentRepository.deleteById(studentId);
        logger.info("Student ID deleted:  {} ", studentId);
    }
}
