package com.LMS.LibraryManagementSystem.Entity;

import com.LMS.LibraryManagementSystem.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int price;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    private boolean isIssued;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Transaction> transactionList=new ArrayList<>();

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    LibraryCard card;

}
