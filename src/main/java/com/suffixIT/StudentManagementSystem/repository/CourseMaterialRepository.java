package com.suffixIT.StudentManagementSystem.repository;

import com.suffixIT.StudentManagementSystem.entity.CourseMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterialEntity, Long> {


}
