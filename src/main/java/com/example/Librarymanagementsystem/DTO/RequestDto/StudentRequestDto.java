package com.example.Librarymanagementsystem.DTO.RequestDto;

import com.example.Librarymanagementsystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {
    private String name;
    private int age;
    private Department department;
    private String mobNo;

}
