package com.suffixIT.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"teachers", "students"})
public class AllCoursesResponseModel {
    private Long courseId;
    private String title;
    private Integer credit;
}
