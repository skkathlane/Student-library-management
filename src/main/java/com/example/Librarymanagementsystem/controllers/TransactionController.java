package com.example.Librarymanagementsystem.controllers;

import com.example.Librarymanagementsystem.DTO.RequestDto.IssueBookRequestDto;
import com.example.Librarymanagementsystem.DTO.RequestDto.ReturnBookRequestDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.IssueBookResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.ReturnBookResponseDto;
import com.example.Librarymanagementsystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue")
    public IssueBookResponseDto issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception {
        return  transactionService.issueBook(issueBookRequestDto);

    }
    @PostMapping("/returnBook")
    public ReturnBookResponseDto returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto) throws Exception {
       return transactionService.returnBook(returnBookRequestDto);
    }
}
