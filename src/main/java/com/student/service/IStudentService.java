package com.student.service;

import java.util.List;

import com.student.model.Student;

public interface IStudentService {

    public Student saveStudents(Student student);
    public List<Student> getAllStudents();
    public Student getStudentById(Long id);
    public void deleteStudentById(Long id);
    public void updateStudent(Student Student);

}
