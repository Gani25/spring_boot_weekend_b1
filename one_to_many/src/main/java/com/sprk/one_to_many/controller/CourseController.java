package com.sprk.one_to_many.controller;

import com.sprk.one_to_many.entity.Course;
import com.sprk.one_to_many.entity.Student;
import com.sprk.one_to_many.repository.CourseRepository;
import com.sprk.one_to_many.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/add-course/{rollNo}")
    public ResponseEntity<?> addCourse(@PathVariable int rollNo, @RequestBody Course course) {

        Student existingStudent = studentRepository.findById(rollNo).orElse(null);
        if (existingStudent == null) {

            return ResponseEntity.status(404).body("Student with roll no = " + rollNo + " not found");
        } else {
            // Logic to add course
            course.setStudent(existingStudent);
            courseRepository.save(course);
            List<Course> courses = existingStudent.getCourses();
            if (courses == null) {
                courses = new ArrayList<>();
            }
            courses.add(course);
            existingStudent.setCourses(courses);
            return ResponseEntity.status(201).body(existingStudent);
        }

    }

    @PostMapping("/add-courses/{rollNo}")
    public ResponseEntity<?> addCourses(@PathVariable int rollNo, @RequestBody List<Course> courses) {

        Student existingStudent = studentRepository.findById(rollNo).orElse(null);
        if (existingStudent == null) {

            return ResponseEntity.status(404).body("Student with roll no = " + rollNo + " not found");
        } else {

            // CHeck if there is only one course
            if (courses.size() == 1) {
                addCourse(rollNo, courses.get(0));
            }
            // Logic to add courses
            List<Course> savedCourses = new ArrayList<>();
            for (Course course : courses) {
                course.setStudent(existingStudent);
                Course savedCourse = courseRepository.save(course);
                savedCourses.add(savedCourse);
            }

            List<Course> existingStudentCourses = existingStudent.getCourses();
            if (existingStudentCourses == null) {
                existingStudentCourses = new ArrayList<>();
            }
            existingStudentCourses.addAll(savedCourses);
            existingStudent.setCourses(existingStudentCourses);
            return ResponseEntity.status(201).body(existingStudent);

        }

    }

    @DeleteMapping("/delete-course/{rollNo}/{courseName}")
    public ResponseEntity<?> deleteCourse(@PathVariable int rollNo, @PathVariable String courseName) {
        Student existingStudent = studentRepository.findById(rollNo).orElse(null);
        if (existingStudent == null) {
            return ResponseEntity.status(404).body("Student with roll no = " + rollNo + " not found");
        }
        List<Course> existingStudentCourses = existingStudent.getCourses();
        boolean foundCourse = false;
        int courseId=0;
        for (Course course : existingStudentCourses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                foundCourse = true;
                courseId = course.getCourseId();
                existingStudentCourses.remove(course);
            }
        }
        existingStudent.setCourses(existingStudentCourses);
        if(foundCourse) {
            Course existingCourse = courseRepository.findById(courseId).get();
            courseRepository.delete(existingCourse);
            return ResponseEntity.ok(existingStudent);
        }else{
            return ResponseEntity.status(404).body("Student with roll no = " + rollNo + " and course = "+courseName+" not found");
        }
    }


}
