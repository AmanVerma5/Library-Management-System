package com.LMS.LibraryManagementSystem.Service;

import com.LMS.LibraryManagementSystem.DTO.AddStudentRequestDto;
import com.LMS.LibraryManagementSystem.DTO.StudentUpdateEmailRequestDto;
import com.LMS.LibraryManagementSystem.DTO.StudentUpdateEmailResponseDto;
import com.LMS.LibraryManagementSystem.Entity.LibraryCard;
import com.LMS.LibraryManagementSystem.Entity.Student;
import com.LMS.LibraryManagementSystem.Enum.CardStatus;
import com.LMS.LibraryManagementSystem.Repository.StudentRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public void addStudent(AddStudentRequestDto addStudentRequestDto) {

        Student student=new Student();
        student.setName(addStudentRequestDto.getName());
        student.setEmail(addStudentRequestDto.getEmail());
        student.setAge(addStudentRequestDto.getAge());
        student.setDepartment(addStudentRequestDto.getDepartment());

        LibraryCard card=new LibraryCard();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);
        studentRepository.save(student);
    }

    public String findByEmail(String email) throws Exception {
        Student student;
        try {
            student = studentRepository.findByEmail(email);
            if(student==null) throw new NullPointerException();
        }catch(Exception e){
            throw new Exception("Email does not exists!");
        }
       return student.getName();
    }

    public List<String> findAllStudentsByAge(int age) {

        List<Student> studentList=studentRepository.findAllByAge(age);

        List<String> nameList=new ArrayList<>();
        for(Student student:studentList){
            String name=student.getName();
            nameList.add(name);
        }

        return nameList;
    }

    public StudentUpdateEmailResponseDto updateStudentEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)throws Exception {

        Student student;
        try {
             student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        }catch(Exception e){
            throw new Exception("Student with given email id not found!");
        }
        if (studentRepository.existsByEmail(studentUpdateEmailRequestDto.getEmail())) {
            throw new Exception("Email id already present!");
        } else {
            student.setEmail(studentUpdateEmailRequestDto.getEmail());
        }
        Student updatedStudent=studentRepository.save(student);

        StudentUpdateEmailResponseDto studentUpdateEmailResponseDto=new StudentUpdateEmailResponseDto();
        studentUpdateEmailResponseDto.setEmail(updatedStudent.getEmail());
        studentUpdateEmailResponseDto.setName(updatedStudent.getName());

        return studentUpdateEmailResponseDto;
    }


}
