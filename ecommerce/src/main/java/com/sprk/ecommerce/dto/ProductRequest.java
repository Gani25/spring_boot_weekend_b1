package com.sprk.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ProductRequest {

    private int productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal productPrice;

    @NotBlank(message = "Description is required")
    private String productDescription;

    @NotEmpty(message = "Please provide atleast on image URL")
    private List<@NotBlank(message = "URL cannot be blank") @Pattern(regexp="^(https?://[^\\s]+)$",message = "Invalid image URL format")String> imageUrls ;
}
