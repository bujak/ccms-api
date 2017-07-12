package com.codecool.ccms.api.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/init")
    public void init() {
        studentService.add(new Student("jan", "kowalski", "krk"));
        studentService.add(new Student("jonh", "doe", "krk"));
    }

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity index() {
        return studentService.getAll();
    }

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable String id) {
        return studentService.getById(id);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity destroy(@PathVariable String id) {

        return studentService.destroy(id);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Student student) {
        return studentService.update(id, student);
    }



}
