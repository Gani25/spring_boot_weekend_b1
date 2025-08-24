package com.sprk.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "product")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    private String imagePathUrl;

    // Many To One
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;

}
