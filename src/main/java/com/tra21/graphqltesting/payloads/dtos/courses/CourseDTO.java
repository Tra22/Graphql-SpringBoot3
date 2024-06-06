package com.tra21.graphqltesting.payloads.dtos.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO implements Serializable {
    private Long id;
    private String name;
}
