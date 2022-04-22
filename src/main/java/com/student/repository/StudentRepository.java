package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query(value="select * from student e where e.first_name like %:keyword% or e.last_name like %:keyword%",nativeQuery=true)
	
	
	
	 List<Student> findByKeyword(@Param("keyword") String keyword);

}