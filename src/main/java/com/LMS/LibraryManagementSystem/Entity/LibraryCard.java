package com.LMS.LibraryManagementSystem.Entity;

import com.LMS.LibraryManagementSystem.Enum.CardStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardNo;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updationDate;
    
    @OneToOne
    @JoinColumn
    @JsonBackReference
    Student student;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Transaction> transactionList=new ArrayList<>();

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Book> bookIssued=new ArrayList<>();

}
