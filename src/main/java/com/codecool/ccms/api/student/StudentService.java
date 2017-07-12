package com.codecool.ccms.api.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity destroy(String id) {
        Student student = getById(id);
        if (student == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        studentRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity update(String id, Student student) {

        Student studentToUpdate = getById(id);
        if (studentToUpdate == null) {
            return null;
        }
        if (student.getFirstName() != null) {
            studentToUpdate.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            studentToUpdate.setLastName(student.getLastName());
        }
        if (student.getKlass() != null) {
            studentToUpdate.setKlass(student.getKlass());
        }
        studentRepository.save(studentToUpdate);
        return new ResponseEntity(getById(id), HttpStatus.OK);
    }
}
