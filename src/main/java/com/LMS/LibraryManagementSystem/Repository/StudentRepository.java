package com.LMS.LibraryManagementSystem.Repository;

import com.LMS.LibraryManagementSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public Student findByEmail(String email);

    public List<Student> findAllByAge(int age);

    boolean existsByEmail(String email);
}
