package com.sprk.ecommerce.controller;

import com.sprk.ecommerce.dto.ProductRequest;
import com.sprk.ecommerce.entity.Image;
import com.sprk.ecommerce.entity.Product;
import com.sprk.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {

        List<ProductRequest> productRequestList = productService.getAllProducts();
        model.addAttribute("productLists", productRequestList);
        return "index";
    }

    @GetMapping("/admin/product")
    public String showProductForm(Model model){
        ProductRequest productRequest = new ProductRequest();
        model.addAttribute("productRequest", productRequest);
        return "productform";
    }

    // Get Product by id
    @GetMapping("/product/{productId}")
    public String showIndividualProduct(@PathVariable Long productId, Model model){

        return "individual-product";
    }

    @PostMapping("/admin/product")
    public String processProductForm(@Valid @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
//            System.out.println(productRequest);
            return "productform";
        }
        else{
//            System.out.println(productRequest);
            boolean result = productService.saveProduct(productRequest);
            if(result){
                String message = "Product: "+productRequest.getProductName()+" has been saved";
                redirectAttributes.addFlashAttribute("successMsg",message);
                return "redirect:/";
            }else{

                String message = "Some thing wrong happen!!";
                redirectAttributes.addFlashAttribute("errorMsg",message);
                return "redirect:/";
            }
        }





    }
}
