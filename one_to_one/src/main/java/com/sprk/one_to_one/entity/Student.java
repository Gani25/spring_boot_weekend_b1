package com.sprk.one_to_one.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    private String firstName;

    private String lastName;

    private String phone;

    // Aggregation
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="student_detail_id" )
    @JsonManagedReference
    private StudentDetail studentDetail;
}
