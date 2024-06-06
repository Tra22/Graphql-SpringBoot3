package com.tra21.graphqltesting.payloads.res.students;

import com.tra21.graphqltesting.payloads.dtos.students.StudentDTO;
import com.tra21.graphqltesting.payloads.res.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentContents extends Response<StudentDTO> {
}
