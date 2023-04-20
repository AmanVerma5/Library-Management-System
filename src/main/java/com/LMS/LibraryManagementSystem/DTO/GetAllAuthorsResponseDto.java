package com.LMS.LibraryManagementSystem.DTO;

import com.LMS.LibraryManagementSystem.Entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetAllAuthorsResponseDto {

    private int id;

    private String name;
    private int age;
    private String mobNo;
    private String email;
    List<Book> books=new ArrayList<>();
}
