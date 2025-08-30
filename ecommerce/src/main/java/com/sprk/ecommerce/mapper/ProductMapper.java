package com.sprk.ecommerce.mapper;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product mapProductRequestToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductId(productRequest.getProductId());
        product.setProductPrice(productRequest.getProductPrice().doubleValue());
        List<String> imageUrls = productRequest.getImageUrls();
        List<Image> images = imageUrls.stream().map((url)->{
                    Image image = new Image();
                    image.setImagePathUrl(url);
                    image.setProduct(product);
                    return image;
                }).toList();

        product.setProductImages(images);

        return product;
    }


}
