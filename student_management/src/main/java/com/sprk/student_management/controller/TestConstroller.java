package com.sprk.student_management.controller;

import com.sprk.student_management.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConstroller {

    @GetMapping("/test")
    public Student test(@RequestBody Student student) {

        System.out.println(student);

        student.setRollNo(100);

        return student;
    }
}
