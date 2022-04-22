package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import com.student.repository.StudentRepository;
import com.student.service.IStudentService;

	@Controller
	@RequestMapping("/student")
	public class StudentController {

	    @Autowired   
	    private IStudentService service;

	    @GetMapping("/")
	    public String showHomePage() {
	       return "homePage";
	    }

	    @GetMapping("/register")
	    public String showRegistration() {
	       return "registerStudentPage";
	    }
	    
	    

	    @PostMapping("/save")
	    public String saveStudent(
	            @ModelAttribute Student students,
	            Model model
	            ) {
	        service.saveStudents(students);
	        Long id = service.saveStudents(students).getId();
	        String message = "Record with id : '"+id+"' is saved successfully !";
	        model.addAttribute("message", message);
	        return "registerStudentPage";
	    }
	    

	    @GetMapping("/getAllStudents")
	    public String getAllStudents(
	            @RequestParam(value = "message", required = false) String message,String keyword,
	            Model model
	            ) {
	    		List<Student> students= service.getAllStudents();
	   	       model.addAttribute("message", message);
	    	if(keyword !=null) {
	    		List<Student> filteredresult=service.findByKeyword(keyword);
	    		model.addAttribute("list",filteredresult);
	    	}
	    	else {
		   	       model.addAttribute("list", students);

	    	}
		       return "allStudentsPage";

	    }

	    

	    @GetMapping("/edit")
	    public String getEditPage(
	            Model model,
	            RedirectAttributes attributes,
	            @RequestParam Long id
	            ) {
	       String page = null; 
	       try {
	       Student student = service.getStudentById(id);
	       model.addAttribute("student", student);
	       page="editStudentPage";
	       } catch (StudentNotFoundException e) {
	           e.printStackTrace();
	           attributes.addAttribute("message", e.getMessage());
	           page="redirect:getAllStudents";
	       }
	       return page; 
	    }

	    @PostMapping("/update")
	    public String updateStudent(
	            @ModelAttribute Student student,
	            RedirectAttributes attributes
	            ) {
	       service.updateStudent(student);
	       Long id = student.getId();
	       attributes.addAttribute("message", "Student with id: '"+id+"' is updated successfully !");
	       return "redirect:getAllStudents";
	    }

	    @GetMapping("/delete")
	    public String deleteStudent(
	            @RequestParam Long id,
	            RedirectAttributes attributes
	            ) {
	        try {
	        service.deleteStudentById(id);
	        attributes.addAttribute("message", "Student with Id : '"+id+"' is removed successfully!");
	        } catch (StudentNotFoundException e) {
	            e.printStackTrace();
	            attributes.addAttribute("message", e.getMessage());
	        }
	        return "redirect:getAllStudents";
	    }
	}


