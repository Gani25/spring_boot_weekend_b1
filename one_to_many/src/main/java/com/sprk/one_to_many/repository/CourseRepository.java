package com.sprk.one_to_many.repository;

import com.sprk.one_to_many.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
