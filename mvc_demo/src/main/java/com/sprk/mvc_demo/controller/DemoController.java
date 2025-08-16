package com.sprk.mvc_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class DemoController {

    @GetMapping("/mvc-demo")
    public String showDemoPage(Model model){
        model.addAttribute("todaysDate", new Date());
        return "demo";
    }

}
