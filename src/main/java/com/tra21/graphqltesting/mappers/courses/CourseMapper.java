package com.tra21.graphqltesting.mappers.courses;

import com.tra21.graphqltesting.models.courses.Course;
import com.tra21.graphqltesting.payloads.dtos.courses.CourseDTO;
import com.tra21.graphqltesting.repositories.courses.CourseRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {
    @Autowired
    private CourseRepository courseRepository;
    public abstract Course map(CourseDTO courseDTO);
    public abstract CourseDTO mapReverse(Course course);
    public abstract List<CourseDTO> mapListReverse(List<Course> courses);
    public List<Course> mapIdsToCourseList(List<Long> ids){
        return courseRepository.findAllById(ids);
    }
}
