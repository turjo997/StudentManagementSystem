package com.suffixIT.StudentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.suffixIT.StudentManagementSystem.entity.CourseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"teachers", "students"})
public class AllCourseMaterialResponseModel {

    private Long courseMaterialId;
    private String url;
    private List<CourseDetails> courses;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseDetails {
        private Long courseId;
        private String title;
        private Integer credit;
    }

}
