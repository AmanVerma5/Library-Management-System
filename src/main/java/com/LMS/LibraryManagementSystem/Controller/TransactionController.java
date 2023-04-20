package com.LMS.LibraryManagementSystem.Controller;

import com.LMS.LibraryManagementSystem.DTO.IssueBookRequestDto;
import com.LMS.LibraryManagementSystem.DTO.IssueBookResponseDto;
import com.LMS.LibraryManagementSystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto)  {

        IssueBookResponseDto response;
        try{
            response=transactionService.issueBook(issueBookRequestDto);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestParam  int bookId){


        try{
            transactionService.returnBook(bookId);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>("Successfully returned",HttpStatus.ACCEPTED);
    }
}
