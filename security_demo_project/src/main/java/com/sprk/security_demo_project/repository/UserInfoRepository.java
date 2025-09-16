package com.sprk.security_demo_project.repository;

import com.sprk.security_demo_project.entity.Role;
import com.sprk.security_demo_project.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
