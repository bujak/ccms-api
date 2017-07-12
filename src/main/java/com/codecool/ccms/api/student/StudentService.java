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

    private final String error404JSON = "{\n" +
            "  \"key\": \"NotFoundError\",\n" +
            "  \"msg\": \"Resource not found\"\n" +
            "}";

    private String getError404JSON() {
        return this.error404JSON;
    }

    public ResponseEntity getAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return new ResponseEntity(students, HttpStatus.OK);
    }

    public ResponseEntity add(Student student) {
        StringBuilder params = new StringBuilder();

        //TODO GENERATE UNFILLED PARAMS
        if (student.getFirstName() == null) {
            params.append("\"firstName\" : \"required\"");
        }
        if (student.getLastName() == null) {
            params.append("\"lastName\" : \"required\"");
        }
        if (student.getKlass() == null) {
            params.append("\"klass\" : \"required\"");
        }
        if (params.length() != 0) {
            return new ResponseEntity(params, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        studentRepository.save(student);
        return new ResponseEntity(student, HttpStatus.CREATED);
    }

    public ResponseEntity getById(String id) {
        Student student = studentRepository.findOne(id);
        if (student == null) {
            return new ResponseEntity(getError404JSON(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(student, HttpStatus.OK);
    }

    public ResponseEntity destroy(String id) {
        HttpStatus httpStatus = getById(id).getStatusCode();
        if (httpStatus == HttpStatus.NOT_FOUND) {
            return new ResponseEntity(getError404JSON(), HttpStatus.NOT_FOUND);
        }
        studentRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity update(String id, Student student) {
        HttpStatus httpStatus = getById(id).getStatusCode();
        if (httpStatus == HttpStatus.NOT_FOUND) {
            return new ResponseEntity(getError404JSON(), HttpStatus.NOT_FOUND);
        }
        Student studentToUpdate = studentRepository.findOne(id);
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
