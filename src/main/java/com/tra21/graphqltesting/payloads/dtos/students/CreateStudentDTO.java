package com.tra21.graphqltesting.payloads.dtos.students;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateStudentDTO implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private boolean isDeleted;
    private List<Long> courses;
}
