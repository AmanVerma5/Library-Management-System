package com.LMS.LibraryManagementSystem.Service;

import com.LMS.LibraryManagementSystem.DTO.IssueBookRequestDto;
import com.LMS.LibraryManagementSystem.DTO.IssueBookResponseDto;
import com.LMS.LibraryManagementSystem.Entity.Book;
import com.LMS.LibraryManagementSystem.Entity.LibraryCard;
import com.LMS.LibraryManagementSystem.Entity.Transaction;
import com.LMS.LibraryManagementSystem.Enum.CardStatus;
import com.LMS.LibraryManagementSystem.Enum.TransactionStatus;
import com.LMS.LibraryManagementSystem.Repository.BookRepository;
import com.LMS.LibraryManagementSystem.Repository.CardRepository;
import com.LMS.LibraryManagementSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private JavaMailSender emailSender;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        Transaction transaction=new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);


        LibraryCard card;
        try {
             card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Card Id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Card Id");
        }

        Book book;
        try{
            book=bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Book Id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book Id");
        }

        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getCardStatus()!= CardStatus.ACTIVATED) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("Card is not activated");
        }

        if(book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Book is already issued");
            transactionRepository.save(transaction);
            throw new Exception("Book is already issued");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Transaction was successful");
        book.setIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBookIssued().add(book);

        cardRepository.save(card);

        IssueBookResponseDto issueBookResponseDto=new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());

        String text="Hello!!. " +card.getStudent().getName()+" Your book with the title "+book.getTitle()+" by"+book.getAuthor().getName()+" have been issued.";
        text+=" Your transaction number is "+transaction.getTransactionNumber();

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("noreply@library.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book Notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;

    }

    public void returnBook(int bookId) throws Exception {

        Transaction transaction=new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(false);

        Book book;
        try{
            book=bookRepository.findById(bookId).get();
        }catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Book was not found!");
            transactionRepository.save(transaction);
            throw new Exception("Book not found!");
        }
        if(book.isIssued()==false){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Book was not issued");
            transactionRepository.save(transaction);
            throw new Exception("Book was not issued");
        }
        LibraryCard card=book.getCard();

        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Book was successfully returned");
        book.setIssued(false);


 //     book.getTransactionList().add(transaction);
        card.getTransactionList().add(transaction);
        book.setCard(null);

        cardRepository.save(card);

    }
}
