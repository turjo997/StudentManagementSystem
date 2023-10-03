package com.suffixIT.StudentManagementSystem.repository;

import com.suffixIT.StudentManagementSystem.entity.CourseMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterialEntity, Long> {

    @Query("SELECT cm FROM CourseMaterialEntity cm WHERE cm.courseEntity.courseId = :courseId")
    List<CourseMaterialEntity> findByCourseId(@Param("courseId") Long courseId);

}
