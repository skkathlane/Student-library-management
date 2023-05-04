package com.example.Librarymanagementsystem.controllers;

import com.example.Librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.Librarymanagementsystem.DTO.RequestDto.UpdateMobNoRequestDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.StudentResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.UpdateMobNoResponseDto;
import com.example.Librarymanagementsystem.entity.Student;
import com.example.Librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.Librarymanagementsystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent((studentRequestDto));

    }
//    delete a student by id
//    update a student by id
//    find student by id
    //find all student
    @PutMapping("/updateMobNo")
    public UpdateMobNoResponseDto updateMobNo(@RequestBody  UpdateMobNoRequestDto updateMobNoRequestDto) throws StudentNotFoundException {
        return studentService.updateMobNo(updateMobNoRequestDto);
    }
    @GetMapping("/getStudent")
    public StudentResponseDto getStudent( @RequestParam("id") int id){
        return studentService.getStudent(id);
    }

}
