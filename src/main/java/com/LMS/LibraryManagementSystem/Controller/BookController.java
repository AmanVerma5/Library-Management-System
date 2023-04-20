package com.LMS.LibraryManagementSystem.Controller;

import com.LMS.LibraryManagementSystem.DTO.AddBookRequestDto;
import com.LMS.LibraryManagementSystem.Entity.Book;
import com.LMS.LibraryManagementSystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody AddBookRequestDto addBookRequestDto){
        try{
            bookService.addBook(addBookRequestDto);
        }catch(Exception e){
            return e.getMessage();
        }
        return "Successfully added";
    }

    @GetMapping("/get-all")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}
