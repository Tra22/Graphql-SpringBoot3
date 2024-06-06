package com.tra21.graphqltesting.controllers;

import com.tra21.graphqltesting.payloads.dtos.students.CreateStudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.UpdateStudentDTO;
import com.tra21.graphqltesting.payloads.req.PaginationReq;
import com.tra21.graphqltesting.payloads.res.students.StudentContents;
import com.tra21.graphqltesting.services.students.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;
    @QueryMapping
    public StudentDTO getStudents(@Argument Long studentId ) {
        return studentService.getStudents(studentId);
    }
    @QueryMapping
    public StudentContents allStudents(@Argument PaginationReq paginationReq) {
        return studentService.allStudents(paginationReq);
    }
    @MutationMapping
    public StudentDTO createStudent(@Argument CreateStudentDTO createStudentDTO){
        return studentService.createStudent(createStudentDTO);
    }
    @MutationMapping
    public StudentDTO updateStudent(@Argument Long studentId, @Argument UpdateStudentDTO updateStudentDTO){
        return studentService.updateStudent(studentId, updateStudentDTO);
    }
    @MutationMapping
    public String deleteStudent(@Argument Long studentId){
        return studentService.deleteStudent(studentId) ? "Success deleted student." : "Failed to delete student.";
    }
}
