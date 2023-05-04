package com.example.Librarymanagementsystem.services.impl;

import com.example.Librarymanagementsystem.DTO.RequestDto.StudentRequestDto;
import com.example.Librarymanagementsystem.DTO.RequestDto.UpdateMobNoRequestDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.CardResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.StudentResponseDto;
import com.example.Librarymanagementsystem.DTO.ResponseDto.UpdateMobNoResponseDto;
import com.example.Librarymanagementsystem.entity.Card;
import com.example.Librarymanagementsystem.entity.Student;
import com.example.Librarymanagementsystem.enums.CardStatus;
import com.example.Librarymanagementsystem.exceptions.StudentNotFoundException;
import com.example.Librarymanagementsystem.repository.StudentRepository;
import com.example.Librarymanagementsystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {
@Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {
        Student student=new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());

        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setValidTill("2024-01-01");
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);

        return "student added";
    }

    @Override
    public StudentResponseDto getStudent(int id) {
        Student student=new Student();
        student =studentRepository.findById(id).get();
        StudentResponseDto studentResponseDto=new StudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setMobNo(student.getMobNo());
        CardResponseDto cardResponseDto=new CardResponseDto();
        cardResponseDto.setIssueDate(student.getCard().getIssueDate());
        cardResponseDto.setId(student.getCard().getId());
        cardResponseDto.setCardStatus(student.getCard().getCardStatus());
        cardResponseDto.setValidTill(student.getCard().getValidTill());
        cardResponseDto.setLastUpdate(student.getCard().getLastUpdate());
        cardResponseDto.setIssueDate(student.getCard().getIssueDate());
        studentResponseDto.setCardResponseDto(cardResponseDto);



        return studentResponseDto;
    }

    @Override
    public UpdateMobNoResponseDto updateMobNo(UpdateMobNoRequestDto updateMobNoRequestDto) throws StudentNotFoundException {
        try{
            Student student=studentRepository.findById(updateMobNoRequestDto.getId()).get();
            student.setMobNo(updateMobNoRequestDto.getMobNo());
            Student updateStudent=studentRepository.save(student);
            UpdateMobNoResponseDto updateMobNoResponseDto=new UpdateMobNoResponseDto();
            updateMobNoResponseDto.setName(updateStudent.getName());
            updateMobNoResponseDto.setMobNo(updateStudent.getMobNo());
            return updateMobNoResponseDto;
        }
        catch(Exception e){
            throw new StudentNotFoundException("invalid id");
        }


    }
}
