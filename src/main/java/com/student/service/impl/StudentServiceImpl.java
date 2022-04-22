package com.student.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    private StudentRepository repo;

    @Override
    public Student saveStudents(Student student) {
       return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
       return repo.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
       Optional<Student> opt = repo.findById(id);
       if(opt.isPresent()) {
           return opt.get();
       } else {
           throw new StudentNotFoundException("Student with Id : "+id+" Not Found");
       }
    }

    @Override
    public void deleteStudentById(Long id) {
       repo.delete(getStudentById(id)); 
    }

    @Override
    public void updateStudent(Student student) {
       repo.save(student);
    }
    
    public List<Student> findByKeyword(String keyword) {
    	
		return repo.findByKeyword(keyword);
	}
}
