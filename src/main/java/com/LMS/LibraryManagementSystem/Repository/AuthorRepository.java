package com.LMS.LibraryManagementSystem.Repository;

import com.LMS.LibraryManagementSystem.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
