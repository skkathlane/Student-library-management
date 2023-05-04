package com.example.Librarymanagementsystem.services;

import com.example.Librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.Librarymanagementsystem.DTO.RequestDto.UpdateMobNoRequestDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.StudentResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.UpdateMobNoResponseDto;
import com.example.Librarymanagementsystem.entity.Student;
import com.example.Librarymanagementsystem.exceptions.StudentNotFoundException;

public interface StudentService {
    public  String addStudent(StudentRequestDto studentRequestDto);
    public StudentResponseDto getStudent(int id);
    public UpdateMobNoResponseDto updateMobNo(UpdateMobNoRequestDto updateMobNoRequestDto) throws StudentNotFoundException;

}
