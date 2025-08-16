package com.sprk.mvc_demo.controller;

import com.sprk.mvc_demo.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DemoController {

    @GetMapping("/mvc-demo")
    public String showDemoPage(Model model){
        model.addAttribute("todaysDate", new Date());
        return "demo";
    }

    @GetMapping("/show-student")
    public String showStudentPage(Model model){
        Student student = new Student();
        student.setRollNo(10);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@gmail.com");
        student.setPhone("+91 12345 12345");
        student.setGender("Male");

        model.addAttribute("student", student);
        return "student-info";
    }
    @GetMapping("/students")
    public String showStudents(Model model){
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setRollNo(10);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@gmail.com");
        student.setPhone("+91 12345 12345");
        student.setGender("Male");
        student.setPrime(true);
        
        students.add(student);

        Student student2 = new Student();
        student2.setRollNo(1);
        student2.setFirstName("Shubham");
        student2.setLastName("Palande");
        student2.setEmail("shubh12@gmail.com");
        student2.setPhone("+91 88888 12345");
        student2.setGender("Male");
        student2.setPrime(false);
        students.add(student2);

        Student student3 = new Student();
        student3.setRollNo(5);
        student3.setFirstName("Pranjali");
        student3.setLastName("Sharma");
        student3.setEmail("prsharma@gmail.com");
        student3.setPhone("+91 11111 99999");
        student3.setGender("Female");
        student3.setPrime(true);
        students.add(student3);

        Student student4 = new Student();
        student4.setRollNo(3);
        student4.setFirstName("Ayushi");
        student4.setLastName("Shinde");
        student4.setEmail("ayushi@gmail.com");
        student4.setPhone("+91 12345 99999");
        student4.setGender("Female");
        student4.setPrime(false);
        students.add(student4);
        students.sort((s1,s2)->Integer.compare(s1.getRollNo(), s2.getRollNo()));
        model.addAttribute("students", students);
        return "student-list";
    }

}
