package com.codecool.ccms.api.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bujak on 12.07.17.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public void add(Student student) {
        studentRepository.save(student);
    }

    public Student getById(String id) {
       return studentRepository.findOne(id);
    }
}
