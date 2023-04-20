package com.LMS.LibraryManagementSystem.DTO;

import com.LMS.LibraryManagementSystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddStudentRequestDto {
    private String name;
    private int age;

    private String email;

    private Department department;
}
