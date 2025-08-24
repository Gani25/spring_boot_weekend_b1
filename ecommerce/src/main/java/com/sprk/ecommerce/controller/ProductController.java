package com.sprk.ecommerce.controller;

import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/admin/product")
    public String showProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "productform";
    }

    @PostMapping("/admin/product")
    public String processProductForm(@ModelAttribute("product") Product product, @RequestParam String imageUrls){

        System.out.println("All Images = "+imageUrls);
        String[] imagePaths = imageUrls.split(", ");
        List<Image> imageList = product.getProductImages();
        if(imageList == null ){
            imageList = new ArrayList<>();
        }

        for(String imagePath: imagePaths){
            Image imageObj = new Image();
            imageObj.setImagePathUrl(imagePath);
            imageList.add(imageObj);

        }
        product.setProductImages(imageList);
        System.out.println(product);

        return "#";

    }
}
