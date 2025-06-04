package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getAllStudents(int page) {
        return studentRepository.findAll(PageRequest.of(page, 5));
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        Optional<Student> result = studentRepository.findById(id);
        return result.orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Page<Student> search(String keyword, String field, int page) {
        if ("class".equalsIgnoreCase(field)) {
            return studentRepository.findByStudentClassContainingIgnoreCase(keyword, PageRequest.of(page, 5));
        } else {
            return studentRepository.findByNameContainingIgnoreCase(keyword, PageRequest.of(page, 5));
        }
    }
}
