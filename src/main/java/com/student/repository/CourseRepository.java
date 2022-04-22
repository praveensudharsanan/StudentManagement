package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.student.model.Course;
import com.student.model.Student;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	@Query(value="select * from course e where e.coursename like %:keyword%",nativeQuery=true)
	
	
	
	 List<Course> findByKeyword(@Param("keyword") String keyword);
}