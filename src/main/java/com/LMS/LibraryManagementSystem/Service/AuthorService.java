package com.LMS.LibraryManagementSystem.Service;

import com.LMS.LibraryManagementSystem.DTO.AddAuthorRequestDto;
import com.LMS.LibraryManagementSystem.Entity.Author;
import com.LMS.LibraryManagementSystem.Repository.AuthorRepository;
import com.LMS.LibraryManagementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public void addAuthor(AddAuthorRequestDto addAuthorRequestDto) {
        Author author=new Author();
        author.setName(addAuthorRequestDto.getName());
        author.setEmail(addAuthorRequestDto.getEmail());
        author.setAge(addAuthorRequestDto.getAge());
        author.setMobNo(addAuthorRequestDto.getMobNo());
        authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
