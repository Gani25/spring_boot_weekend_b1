package com.sprk.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;

    private double productPrice;

    private String productDescription;

    // One to Many
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> productImages;
}
