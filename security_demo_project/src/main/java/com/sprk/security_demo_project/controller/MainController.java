package com.sprk.security_demo_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/test")
    public String test() {
        return "testing security";
    }
    @GetMapping("/home")
    public String home() {
        return "Home Page after giving correct username and password security";
    }
}
