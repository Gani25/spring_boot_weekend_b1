package com.sprk.student_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// For class we follow PascalCase
// -> Example StudentInfo
// -> Table will be created with snake case -> student_info
@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
//@Table(name = "student_sprk_info") now table will be created with this name
public class Student extends BaseEntity {

    // Since for columns we use camelCase -> rollNo -> column name will be snake_case -> roll_no
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

//    @Column(name = "user_first_name")
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String email;

    @Column(nullable = false)
    private int age;

    private String gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double percentage;

    // email -> When creating check if already exists or not

    // constructor
    // getters/setters

    // BoilerPlate Code
    // Lombok

}
