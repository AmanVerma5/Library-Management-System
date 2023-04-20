package com.LMS.LibraryManagementSystem.Repository;

import com.LMS.LibraryManagementSystem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {



}
