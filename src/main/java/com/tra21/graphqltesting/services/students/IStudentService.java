package com.tra21.graphqltesting.services.students;

import com.tra21.graphqltesting.payloads.dtos.students.CreateStudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import com.tra21.graphqltesting.payloads.dtos.students.UpdateStudentDTO;
import com.tra21.graphqltesting.payloads.req.PaginationReq;
import com.tra21.graphqltesting.payloads.res.students.StudentContents;

public interface IStudentService {
    StudentDTO getStudents(Long studentId);
    StudentContents allStudents(PaginationReq paginationReq);
    StudentDTO createStudent(CreateStudentDTO createStudentDTO);
    StudentDTO updateStudent(Long studentId, UpdateStudentDTO updateStudentDTO);
    boolean deleteStudent(Long studentId);
}
