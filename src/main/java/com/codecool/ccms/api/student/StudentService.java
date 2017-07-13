package com.codecool.ccms.api.student;

import com.codecool.ccms.api.exceptions.NotFoundException;
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

    public ResponseEntity getAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return new ResponseEntity(students, HttpStatus.OK);
    }

    public void add(Student student) {
        studentRepository.save(student);
    }

    private Student getById(String id) {
        Student student = studentRepository.findOne(id);
        if (student == null) {
            throw new NotFoundException();
        }
        return student;
    }

    public ResponseEntity findOne(String id) {
        return new ResponseEntity(getById(id), HttpStatus.OK);
    }

    public ResponseEntity destroy(String id) {
        getById(id);
        studentRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity update(String id, Student student) {

        Student studentToUpdate = getById(id);
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
        return new ResponseEntity(studentToUpdate, HttpStatus.OK);
    }
}
