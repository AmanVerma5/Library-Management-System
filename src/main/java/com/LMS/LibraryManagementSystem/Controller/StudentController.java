package com.LMS.LibraryManagementSystem.Controller;

import com.LMS.LibraryManagementSystem.DTO.AddStudentRequestDto;
import com.LMS.LibraryManagementSystem.DTO.StudentUpdateEmailRequestDto;
import com.LMS.LibraryManagementSystem.DTO.StudentUpdateEmailResponseDto;
import com.LMS.LibraryManagementSystem.Entity.Student;
import com.LMS.LibraryManagementSystem.Repository.StudentRepository;
import com.LMS.LibraryManagementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

//    @Autowired
//    StudentRepository studentRepository;


    @PostMapping("/add")
    public String addStudent(@RequestBody AddStudentRequestDto addStudentRequestDto){
        studentService.addStudent(addStudentRequestDto);
        return "Successfully added";
    }

    @GetMapping("/find_by_email")
    public String findStudentByEmail(@RequestParam String email){

        Student student;
       String name;
        try {
//             student =studentRepository.findByEmail(email);
             name=studentService.findByEmail(email);
        }catch(Exception e){
            return e.getMessage();
        }
//        return student.getName();
        return name;
    }

    //get all students by a particular age

    @GetMapping("/get_all_byAge")
    public List<String> findAllStudentsByAge(@RequestParam int age){

            return studentService.findAllStudentsByAge(age);

    }

    @PutMapping("/update_email")
    public ResponseEntity<?> updateStudentEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){

        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentService.updateStudentEmail(studentUpdateEmailRequestDto));
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
