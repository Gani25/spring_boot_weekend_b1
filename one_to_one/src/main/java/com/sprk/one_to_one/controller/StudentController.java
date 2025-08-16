package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Student;
import com.sprk.one_to_one.entity.StudentDetail;
import com.sprk.one_to_one.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/save-student")
    public Student saveStudent(@RequestBody Student student) {

        Student savedStudent = studentRepository.save(student);

        return savedStudent;

    }
    @GetMapping("/get-all-student")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/get-by-rollno")
    public Student getStudentByRollno(@RequestParam int rollno) {
        return studentRepository.findById(rollno).orElse(null);
    }

    @PutMapping("/update-student")
    public Student updateStudent(@RequestParam int rollno, @RequestBody Student student) {
        Student existingStudent = studentRepository.findById(rollno).orElse(null);
        student.setRollNo(rollno);
        StudentDetail studentDetail = student.getStudentDetail();
        studentDetail.setStudentDetailId(existingStudent.getStudentDetail().getStudentDetailId());

        student.setStudentDetail(studentDetail);
        return studentRepository.save(student);
    }
}
