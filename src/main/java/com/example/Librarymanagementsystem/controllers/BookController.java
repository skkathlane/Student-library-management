package com.example.Librarymanagementsystem.controllers;

import com.example.Librarymanagementsystem.entity.Book;
import com.example.Librarymanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        try {
            return bookService.addBook(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //find all the book
    //find all the book of  a particular author
    // find the number of book written by an author

}
