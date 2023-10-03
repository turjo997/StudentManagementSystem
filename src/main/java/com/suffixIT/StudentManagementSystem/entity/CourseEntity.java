package com.suffixIT.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private Integer credit;

//    @OneToOne(
//            mappedBy = "courseEntity"
//    )
//    private CourseMaterialEntity courseMaterialEntity;





}
