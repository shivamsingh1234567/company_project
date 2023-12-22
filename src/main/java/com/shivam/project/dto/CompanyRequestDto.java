package com.shivam.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyRequestDto {
    private String name;
    private String department;
    private String contact;
    private float revenue;
}
