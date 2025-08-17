package com.sprk.mvc_demo.controller;

import com.sprk.mvc_demo.entity.Student;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/showform")
    public String showForm(){
        return "student-form";
    }

    @PostMapping("/process")
    public String processForm(@RequestParam(name = "first_name") String firstName, @RequestParam(name = "last_name") String lastName, @RequestParam(name = "email") String email, HttpSession session, RedirectAttributes redirectAttributes){
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("email", email);
        redirectAttributes.addFlashAttribute("msg","Student Saved Successfully");
        return "redirect:/confirm";
    }

    @GetMapping("/confirm")
    public String confirm(){

        return "student-confirmation";
    }

    @GetMapping("/showform2")
    public String showForm2(Model model){
        Student student = new Student();
//        student.setRollNo(10);
//        student.setFirstName("Pranjali");
//        student.setLastName("Verma");
//        student.setEmail("pranjali@gmail.com");
        model.addAttribute("student", student);
        return "student-form2";
    }

    @PostMapping("/process2")
    public String processForm2(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "student-form2";
        }
        session.setAttribute("student",student);
        redirectAttributes.addFlashAttribute("msg","Student Saved Successfully");
        return "redirect:/confirm2";
    }

    @GetMapping("/confirm2")
    public String confirm2(){

        return "student-confirmation2";
    }

}
