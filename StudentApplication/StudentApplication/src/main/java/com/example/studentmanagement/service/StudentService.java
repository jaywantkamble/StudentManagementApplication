package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @CircuitBreaker(name = "studentManagement", fallbackMethod = "addOrUpdateStudentFallback")
    public Student updateStudent(Long id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    public void deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // Fallback method for addOrUpdateStudent
    public void addOrUpdateStudentFallback(Student student, Throwable throwable) {
        // Log the error or perform any fallback logic
        System.out.println("Fallback method triggered for addOrUpdateStudent");
        System.out.println("Error: " + throwable.getMessage());
        // You can return a default response or throw a custom exception here
    }

}
