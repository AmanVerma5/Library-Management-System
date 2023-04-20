package com.LMS.LibraryManagementSystem.Entity;

import com.LMS.LibraryManagementSystem.Enum.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionNumber;
    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    private boolean isIssueOperation;

    private String message;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    Book book;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    LibraryCard card;

}
