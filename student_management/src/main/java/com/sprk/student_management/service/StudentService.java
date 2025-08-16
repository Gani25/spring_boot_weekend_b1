package com.sprk.student_management.service;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;

import java.util.List;

public interface StudentService {

    // All the methods name in interface
    // Abstract public
    public StudentDto saveStudent(StudentDto studentDto);

    List<StudentDto> findAllStudents();

    Student findStudentByRollNo(String rollNo);

    List<Student> findAllByGender(String gender);

    boolean deleteStudent(int rollNo);

    Student updateStudent(int rollNo, Student student);
}
