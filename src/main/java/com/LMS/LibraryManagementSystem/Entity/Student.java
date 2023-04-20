package com.LMS.LibraryManagementSystem.Entity;

import com.LMS.LibraryManagementSystem.Enum.Department;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    @JsonManagedReference
    LibraryCard card;
}
