package com.suffixIT.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private Integer age;

    @ManyToMany(cascade=CascadeType.ALL, fetch =FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private List<Course> courses;
}
