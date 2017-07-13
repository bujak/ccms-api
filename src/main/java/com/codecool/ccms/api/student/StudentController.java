package com.codecool.ccms.api.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@Validated @RequestBody Student student) {
        studentService.add(student);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable Integer id) {
        return studentService.findOne(id);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public ResponseEntity destroy(@PathVariable Integer id) {
        return studentService.destroy(id);
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.update(id, student);
    }



}
