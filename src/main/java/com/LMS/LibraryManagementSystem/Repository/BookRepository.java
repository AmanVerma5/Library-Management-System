package com.LMS.LibraryManagementSystem.Repository;

import com.LMS.LibraryManagementSystem.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
