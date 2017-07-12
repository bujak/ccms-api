package com.codecool.ccms.api.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "helol";
    }
}
