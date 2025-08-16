package com.sprk.one_to_many.controller;

import com.sprk.one_to_many.entity.Course;
import com.sprk.one_to_many.entity.Student;
import com.sprk.one_to_many.entity.StudentDto;
import com.sprk.one_to_many.entity.StudentUtil;
import com.sprk.one_to_many.repository.CourseRepository;
import com.sprk.one_to_many.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentUtil studentUtil;

    @PostMapping("/save-student")
    public Student saveStudent(@RequestBody Student student) {

        List<Course> courses = new ArrayList<>();
        // Copy courses from student
        for (Course course : student.getCourses()) {
            courses.add(course);
        }
        student.getCourses().clear();
        // save only student
        Student savedStudent = studentRepository.save(student);
        // save courses wrt that student
        if(courses != null)
        {

            for (Course course:courses){
                course.setStudent(savedStudent);
                Course savedCourse = courseRepository.save(course);

            }
            savedStudent.setCourses(courses);
        }
        return savedStudent;

    }
    @GetMapping("/get-all-student")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/get-by-rollno")
    public StudentDto getStudentByRollno(@RequestParam int rollno) {
        Student dbStudent =  studentRepository.findById(rollno).orElse(null);

        StudentDto dbStudentDto = studentUtil.studentToStudentDto(dbStudent);

        System.out.println("I have fetch Students...");
        System.out.println("I am in method getStudentByRollNo...");

        List<Course> courses = dbStudent.getCourses();
        System.out.println(courses);
        return dbStudentDto;
    }

    // Delete whole student -> Delete all courses associate to that student
    @DeleteMapping("/student/delete/{rollNo}")
    public ResponseEntity<?> deleteStudent(@PathVariable int rollNo) {
       Student dbStudent = studentRepository.findById(rollNo).orElse(null);

       if(dbStudent != null) {
           // Set Student in courses to null
           List<Course> courses = dbStudent.getCourses();
           for(Course course:courses){
               course.setStudent(null);
           }
           studentRepository.delete(dbStudent);
           return ResponseEntity.ok().body("Student with roll no: "+rollNo+" deleted successfully");
       }else {
           return ResponseEntity.status(404).body("Student with roll no: "+rollNo+" not found!!");

       }
    }


}
