package com.shivam.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequestDto {
    private String name;
    private String contact;
    private float salary;
    private String department;
}
