package com.tra21.graphqltesting.repositories.courses;

import com.tra21.graphqltesting.models.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
