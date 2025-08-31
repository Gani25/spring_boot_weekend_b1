package com.sprk.ecommerce.mapper;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductMapper {

    public Product mapProductRequestToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setBrand(productRequest.getBrand());
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

    public ProductRequest mapProductToProductRequest(Product product){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setBrand(product.getBrand());
        productRequest.setProductName(product.getProductName());
        productRequest.setProductDescription(product.getProductDescription());
        productRequest.setProductId(product.getProductId());
        productRequest.setProductPrice(BigDecimal.valueOf(product.getProductPrice()));

        List<Image> images = product.getProductImages();
        List<String> imagesUrls = images.stream().map((image)->{
            String imgUrl = image.getImagePathUrl();
            return imgUrl;
        }).toList();
        productRequest.setImageUrls(imagesUrls);
        return productRequest;

    }


}
