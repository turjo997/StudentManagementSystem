package com.suffixIT.StudentManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseUpdateRequest {

    private Long courseId;
    private String title;
    private Integer credit;


}
