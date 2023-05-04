package com.example.Librarymanagementsystem.services;

import com.example.Librarymanagementsystem.DTO.RequestDto.IssueBookRequestDto;
import com.example.Librarymanagementsystem.DTO.RequestDto.ReturnBookRequestDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.IssueBookResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.ReturnBookResponseDto;

public interface TransactionService {
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception;
    public ReturnBookResponseDto returnBook(ReturnBookRequestDto returnBookRequestDto) throws Exception;
}
