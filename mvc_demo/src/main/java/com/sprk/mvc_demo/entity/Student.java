package com.sprk.mvc_demo.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Student {

    private int rollNo;

    @NotEmpty(message = "First Name Cannot Be Empty")
    private String firstName;

    @NotEmpty(message = "Last Name Cannot Be Empty")
    private String lastName;

    @Email(message = "Enter Valid Email")
    @NotEmpty(message = "Email Cannot Be Empty")
    private String email;


    @Pattern(
            regexp = "^(\\+\\d{1,3})(?:[ ]?\\d{2,5}){2,5}$",
            message = "Invalid phone number format"
    )
    @NotEmpty(message = "Phone Cannot Be Empty")
    private String phone;

    private String gender;

    private boolean prime;



}
