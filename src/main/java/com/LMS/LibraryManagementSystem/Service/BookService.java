package com.LMS.LibraryManagementSystem.Service;

import com.LMS.LibraryManagementSystem.DTO.AddBookRequestDto;
import com.LMS.LibraryManagementSystem.Entity.Author;
import com.LMS.LibraryManagementSystem.Entity.Book;
import com.LMS.LibraryManagementSystem.Repository.AuthorRepository;
import com.LMS.LibraryManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public void addBook(AddBookRequestDto bookRequestDto) throws Exception{
        Author author;
        try {
            author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        }catch(Exception e){
            throw new Exception("Author not found!");
        }
        Book book=new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setAuthor(author);


        author.getBooks().add(book);

        authorRepository.save(author);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
