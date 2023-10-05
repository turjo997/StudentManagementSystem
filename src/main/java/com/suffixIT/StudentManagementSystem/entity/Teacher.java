package com.suffixIT.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacherId;

    private String firstName;
    private String lastName;
    private String gender;
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private Set<Course> courses;
}
