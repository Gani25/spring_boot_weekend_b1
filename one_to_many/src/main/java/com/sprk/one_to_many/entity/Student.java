package com.sprk.one_to_many.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();

}
