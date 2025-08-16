package com.sprk.student_management.constant;

import org.springframework.http.HttpStatus;

public class StudentConstants {

    public static final String EMAIL_EXISTS = "Email = %s already exists";

    public static final int EMAIL_CONFLICT = 409;

    public static final int STUDENT_CREATED = 201;
    public static final String STUDENT_CREATED_MSG = "Student with roll no = %d successfully saved";

    public static final int SUCCESS = 200;
    public static final String GET_ALL_STUDENT = "Fetching all student";
}
