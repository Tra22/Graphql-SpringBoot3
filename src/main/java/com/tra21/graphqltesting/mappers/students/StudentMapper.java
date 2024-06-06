package com.tra21.graphqltesting.mappers.students;

import com.tra21.graphqltesting.mappers.courses.CourseMapper;
import com.tra21.graphqltesting.models.students.Student;
import com.tra21.graphqltesting.payloads.dtos.students.CreateStudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.UpdateStudentDTO;
import com.tra21.graphqltesting.payloads.res.students.StudentContents;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseMapper.class}, imports = {HttpStatus.class})
public interface StudentMapper {
    Student map(StudentDTO studentDTO);
    Student map(CreateStudentDTO createStudentDTO);
    @Mapping(target = "id", ignore = true)
    Student map(@MappingTarget Student student, UpdateStudentDTO updateStudentDTO);
    StudentDTO mapReverse(Student student);
    List<StudentDTO> mapListReverse(List<Student> students);
    @Mappings({
            @Mapping(target = "statusCode", expression="java(HttpStatus.OK.value())"),
            @Mapping(target = "pagination.page", source = "pageable.pageNumber"),
            @Mapping(target = "pagination.size", source = "pageable.pageSize"),
            @Mapping(target = "pagination.totalPage", source = "totalPages"),
            @Mapping(target = "pagination.totalElement", source = "totalElements")
    })
    StudentContents mapPageToStudentContents(Page<Student> studentPage);
}
