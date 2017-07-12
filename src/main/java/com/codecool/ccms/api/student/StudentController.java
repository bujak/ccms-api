package com.codecool.ccms.api.student;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Student> index() {
        return studentService.getAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Student add(@RequestBody Student student) {
        studentService.add(student);
        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student show(@PathVariable String id) {
        return studentService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Integer destroy(@PathVariable String id) {
        studentService.destroy(id);
        return studentService.getAll().size();
    }


}
