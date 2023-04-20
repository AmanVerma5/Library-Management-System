package com.LMS.LibraryManagementSystem.Repository;

import com.LMS.LibraryManagementSystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
