package com.suffixIT.StudentManagementSystem.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreateRequest {

    private Long courseId;
    private String title;
    private Integer credit;
    private String url;


}
