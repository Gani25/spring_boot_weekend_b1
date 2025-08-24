package com.sprk.ecommerce.controller;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productRequest", productRequest);
        return "productform";
    }

    @PostMapping("/admin/product")
    public String processProductForm(@Valid @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult bindingResult){


        return "#";

    }
}
