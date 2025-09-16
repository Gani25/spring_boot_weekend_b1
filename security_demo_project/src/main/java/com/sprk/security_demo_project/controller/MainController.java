package com.sprk.security_demo_project.controller;

import com.sprk.security_demo_project.entity.UserInfo;
import com.sprk.security_demo_project.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserInfoService userInfoService;

    @GetMapping("/test")
    public String test() {
        return "testing security";
    }
    @GetMapping("/home")
    public String home() {
        return "Home Page after giving correct username and password security";
    }

    @GetMapping("/tester/test")
    @PreAuthorize("hasAuthority('ROLE_TESTER')")
    public String tester() {
        return "testing security I am having Tester Role";
    }

    @GetMapping("/admin/show-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showAdmin() {
        return "I am admin";
    }

    @PostMapping("/signup")
    public UserInfo signup(@RequestBody UserInfo userInfo) {
        UserInfo signUpUserInfo = userInfoService.signUpUser(userInfo);

        return signUpUserInfo;
    }
}
