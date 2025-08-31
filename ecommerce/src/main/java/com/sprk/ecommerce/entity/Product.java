package com.sprk.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;

    private String brand;

    private double productPrice;
//    @Column(columnDefinition = "Longtext")
    @Lob
    private String productDescription;

    // One to Many
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> productImages;
}
