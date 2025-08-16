package com.sprk.student_management.controller;

import com.sprk.student_management.constant.StudentConstants;
import com.sprk.student_management.dto.ErrorResponseDto;
import com.sprk.student_management.dto.ResponseDto;
import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "CRUD APIs for Student of SPRK Technologies",
        description = "REST apis of Student in SPRK Technologies like GET, POST, PUT, DELETE"
)
@RequestMapping("/sprk")
public class StudentController {

    // Injecting Service
    private final StudentService studentService;

    // Insert student
    @Operation(
            summary = "Create Student Rest API",
            description = "API to register new student for SPRK Technologies"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email Already Register",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
    })
    @PostMapping("/student")
    public ResponseEntity<ResponseDto<StudentDto>> addStudent(@Valid @RequestBody StudentDto studentDto) {
        // Service Call
        StudentDto savedStudentDto = studentService.saveStudent(studentDto);
        ResponseDto<StudentDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.STUDENT_CREATED);
        responseDto.setMessage(String.format(StudentConstants.STUDENT_CREATED_MSG,savedStudentDto.getRollNo()));
        responseDto.setData(savedStudentDto);
        return ResponseEntity.status(HttpStatus.valueOf(StudentConstants.STUDENT_CREATED)).body(responseDto);
    }

    // Find All Students
    @GetMapping("/student")
    public ResponseEntity<ResponseDto<List<StudentDto>>>  getAllStudents() {
        List<StudentDto> studentDtos = studentService.findAllStudents();
        ResponseDto<List<StudentDto>> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.SUCCESS);
        responseDto.setMessage(StudentConstants.GET_ALL_STUDENT);
        responseDto.setData(studentDtos);
        return ResponseEntity.status(HttpStatus.valueOf(StudentConstants.SUCCESS)).body(responseDto);
    }

    // Find Student By Roll No
    @GetMapping("/student/{rollNo}")
    public Student getStudentByRollNo(@PathVariable String rollNo) {
        Student existingStudent = studentService.findStudentByRollNo(rollNo);

        return existingStudent;
    }

    // Find Student By Gender
    // /student?gender=Male
    @GetMapping("/gender-student")
    public List<Student> getAllByGender(@RequestParam String gender) {
        List<Student> student = studentService.findAllByGender(gender);
        return student;
    }

    // Delete Student
    @DeleteMapping("/student")
    public ResponseEntity<String> deleteStudent(@RequestParam int rollNo) {
        // Service
        boolean isDeleted = studentService.deleteStudent(rollNo);

        if (isDeleted) {
            String msg = String.format("Student with roll no = %d, Deleted Successfully", rollNo);
//            return ResponseEntity.status(HttpStatus.OK).body(msg);
//            return ResponseEntity.status(200).body(msg);
//            return new ResponseEntity<>(msg, HttpStatus.OK);
            return ResponseEntity.ok(msg);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Student with roll no = %d, Not Found", rollNo));


    }

    @PutMapping("/student")
    public ResponseEntity<?> updateStudent(@RequestParam int rollNo, @RequestBody Student student) {

        Student updatedStudent = studentService.updateStudent(rollNo, student);
        if(updatedStudent != null)
        {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Student with roll no = %d, Not Found", rollNo));
    }

}
