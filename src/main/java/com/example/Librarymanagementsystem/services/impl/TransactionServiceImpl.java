package com.example.Librarymanagementsystem.services.impl;

import com.example.Librarymanagementsystem.DTO.RequestDto.IssueBookRequestDto;
import com.example.Librarymanagementsystem.DTO.RequestDto.ReturnBookRequestDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.IssueBookResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.ReturnBookResponseDto;
import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.entity.Card;
import com.example.Librarymanagementsystem.entity.Transaction;
import com.example.Librarymanagementsystem.enums.CardStatus;
import com.example.Librarymanagementsystem.enums.TransactionStatus;
import com.example.Librarymanagementsystem.repository.BookRepository;
import com.example.Librarymanagementsystem.repository.CardRepository;
import com.example.Librarymanagementsystem.repository.TransactionRepository;
import com.example.Librarymanagementsystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

//import static org.aspectj.bridge.Version.text;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {
        Card card;
        Transaction transaction=new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);
        try{
            card=cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("invalid card id");
        }
        transaction.setCard(card);
        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("invalid book id");
        }
        transaction.setBook(book);
        if(card.getCardStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("card is not activate");
        }
        if(book.isAvailable()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("book is not available");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
//         transactionRepository.save(transaction);
        book.setAvailable(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        card.getBookList().add(book);
        card.getTransactionList().add(transaction);


        cardRepository.save(card);// it will save card , book and transaction as well
        //preparing response dto
        IssueBookResponseDto issueBookResponseDto=new IssueBookResponseDto();
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());
        issueBookResponseDto.setBookName(book.getTitle());
        String text ="Congrats!"+card.getStudent().getName()+"you have been issued the book:"+book.getTitle();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplystudent0@gmail.com");
        message.setTo(card.getStudent().getMobNo());
        message.setSubject("Issue book");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;
    }

    @Override
    public ReturnBookResponseDto returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception {
        Transaction transaction=new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        Card card=new Card();
        try{
            card=cardRepository.findById(returnBookRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("invalid card id");

        }
        transaction.setCard(card);
        Book book =new Book();
        try {
            book = bookRepository.findById(returnBookRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("invalid book id");
        }
        transaction.setBook(book);
        if(book.isAvailable()==false){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("you have not issued the book");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        book.getTransactionList().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBookList().remove(book);
        book.setAvailable(false);
        book.setCard(null);

//       cardRepository.save(card);
        transactionRepository.save(transaction);
       ReturnBookResponseDto returnBookResponseDto=new ReturnBookResponseDto();
       returnBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
       returnBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());
       returnBookResponseDto.setBookName(book.getTitle());


       return returnBookResponseDto;
    }


}
