package com.suffixIT.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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


    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "teacher_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "teacherId"
            )
    )
    private List<TeacherEntity> teachers;

    public void addTeachers(TeacherEntity teacher){
        if(teachers == null) teachers = new ArrayList<>();
        teachers.add(teacher);
    }


}
