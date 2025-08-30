package com.sprk.ecommerce.service;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Product;
import com.sprk.ecommerce.mapper.ProductMapper;
import com.sprk.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
