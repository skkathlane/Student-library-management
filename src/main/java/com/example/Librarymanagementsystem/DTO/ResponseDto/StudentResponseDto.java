package com.example.Librarymanagementsystem.DTO.ResponseDto;

import com.example.Librarymanagementsystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentResponseDto {
    private int id;
    private String name;
    private int age;
    private Department department;
    private String mobNo;
    CardResponseDto cardResponseDto;
}
