package com.sprk.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ProductRequest {

    private int productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Price is required")
    @Pattern(regexp = "^[1-9]+$", message = "Price should be greater than 0")
    private double productPrice;

    @NotBlank(message = "Description is required")
    private String productDescription;

    @NotEmpty(message = "Please provide atleast on image URL")
    private List<@Pattern(regexp="^(https?://[^\\s]+)$",message = "Invalid image URL format")String> imageUrls ;
}
