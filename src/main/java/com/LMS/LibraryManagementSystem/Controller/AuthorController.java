package com.LMS.LibraryManagementSystem.Controller;

import com.LMS.LibraryManagementSystem.DTO.AddAuthorRequestDto;
import com.LMS.LibraryManagementSystem.Entity.Author;
import com.LMS.LibraryManagementSystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AddAuthorRequestDto addAuthorRequestDto){

        authorService.addAuthor(addAuthorRequestDto);
        return "Successfully added";
    }

    @GetMapping("/get-authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }


}
