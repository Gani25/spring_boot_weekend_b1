package com.sprk.ecommerce.service;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import com.sprk.ecommerce.mapper.ProductMapper;
import com.sprk.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public boolean saveProduct(ProductRequest productRequest) {
        Product product = productMapper.mapProductRequestToProduct(productRequest);

        Product savedProduct = null;
        savedProduct = productRepository.save(product);

        if (savedProduct != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<ProductRequest> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductRequest> productRequests = products
                .stream()
                .map((product) -> productMapper.mapProductToProductRequest(product))
                .toList();

        return productRequests;

    }

    public ProductRequest getProductById(int productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if(product != null) {
            return productMapper.mapProductToProductRequest(product);
        }
        return null;

    }

    public boolean updateProduct(ProductRequest productRequest) {

        Product product = productMapper.mapProductRequestToProduct(productRequest);// we have product id -> product (id)
        // In product obj -> we dont have image id -> so it will insert same images again
        Product dbProduct1 = productRepository.findById(productRequest.getProductId()).orElse(null);
        List<Image> dbImages1 = dbProduct1.getProductImages();
        List<Image> productUpdateImages = product.getProductImages();
        for(int i = 0; i < productUpdateImages.size(); i++) {
            // Now Image will be updated not inserted
            productUpdateImages.get(i).setImageId(dbImages1.get(i).getImageId());
        }

        Product savedProduct = null;
        savedProduct = productRepository.save(product);

        if (savedProduct != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteProduct(ProductRequest productRequest) {

        Product dbProduct = productRepository.findById(productRequest.getProductId()).get();
        productRepository.delete(dbProduct);
        return true;
    }
}
