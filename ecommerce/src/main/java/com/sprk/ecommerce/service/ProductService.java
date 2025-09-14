package com.sprk.ecommerce.service;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import com.sprk.ecommerce.mapper.ProductMapper;
import com.sprk.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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

        if (product != null) {
            return productMapper.mapProductToProductRequest(product);
        }
        return null;

    }

    /*public boolean updateProduct(ProductRequest productRequest) {

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
    }*/

    // Bcoz of Image Problem We are writing new logic for update
    public boolean updateProduct(ProductRequest productRequest) {

        Product dbProduct1 = productRepository.findById(productRequest.getProductId()).orElse(null);
        List<String> dbImagesUrls = dbProduct1.getProductImages().stream().map(Image::getImagePathUrl).toList();
        List<String> productRequestImagesUrls = productRequest.getImageUrls();

//        System.out.println("Db Images = "+dbImagesUrls+" Size = "+dbImagesUrls.size());
//        System.out.println("Product Images = "+productRequestImagesUrls+" Size = "+productRequestImagesUrls.size());

        // Do change images and clear old ones if in productRequest we find new urls

        if (!productRequestImagesUrls.equals(dbImagesUrls) || productRequestImagesUrls.size() != dbImagesUrls.size()) {
            // work only if there is some changes in image urls
//            System.out.println("Inside If.....");
            dbProduct1.getProductImages().clear();// orphan removal
//            System.out.println("After clear = "+dbProduct1.getProductImages().size());
            for (String imageUrl : productRequestImagesUrls) {

                Image image = new Image();
                image.setImagePathUrl(imageUrl);
                image.setProduct(dbProduct1);

                dbProduct1.getProductImages().add(image);


//            System.out.println("After adding new images = "+dbProduct1.getProductImages().size());
            }
        }

        // If images are same then I only have to update dbproduct1
        dbProduct1.setProductName(productRequest.getProductName());
        dbProduct1.setProductDescription(productRequest.getProductDescription());
        dbProduct1.setProductPrice(productRequest.getProductPrice().doubleValue());
        dbProduct1.setBrand(productRequest.getBrand());

        productRepository.save(dbProduct1);

        return true;


    }

    public boolean deleteProduct(ProductRequest productRequest) {

        Product dbProduct = productRepository.findById(productRequest.getProductId()).get();
        productRepository.delete(dbProduct);
        return true;
    }
}
