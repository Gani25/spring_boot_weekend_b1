package com.sprk.one_to_one.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentDetailId;

    private String hobby;

    private String education;

    // Bi Directional
    @OneToOne(mappedBy = "studentDetail")
    @JsonBackReference
    private Student student;
}
