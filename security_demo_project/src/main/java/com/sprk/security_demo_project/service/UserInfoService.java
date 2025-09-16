package com.sprk.security_demo_project.service;

import com.sprk.security_demo_project.entity.Role;
import com.sprk.security_demo_project.entity.UserInfo;
import com.sprk.security_demo_project.repository.RoleRepository;
import com.sprk.security_demo_project.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserInfo signUpUser(UserInfo userInfo) {

        Role role = roleRepository.findByRoleName("ROLE_USER").get();
        // Encode Password from UserInfo
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        userInfo.getRoles().add(role);

        UserInfo signUpUser = userInfoRepository.save(userInfo);

        return signUpUser;



    }

}
