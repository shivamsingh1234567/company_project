package com.shivam.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeIdRequestDto {
    public List<Long> employeeIdList;
}
