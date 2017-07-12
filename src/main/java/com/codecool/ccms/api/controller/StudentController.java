package com.codecool.ccms.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @RequestMapping("")
    public String index() {
        return "helol";
    }
}
