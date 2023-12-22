package com.shivam.project.controllers;

import com.shivam.project.dto.EmployeeIdRequestDto;
import com.shivam.project.dto.EmployeeRequestDto;
import com.shivam.project.model.Employee;
import com.shivam.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/*
this my comment for employee controller
* */
@RequestMapping("/api/v1")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public Map save(@RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.save(employeeRequestDto);
    }

    @GetMapping("/get-employee-by-id/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/delete-employee-by-id/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id){
        return employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/update-employee-by-id/{id}")
    public Map updateEmployeeById(@PathVariable("id") Long id, @RequestBody EmployeeRequestDto employeeRequestDto){
        return employeeService.updateEmployeeById(id, employeeRequestDto);
    }

    @PutMapping("/employee-update-salary/{id}")
    public Map employeeSalaryUpdate(@PathVariable("id") Long id, @RequestBody Float salary){
        return employeeService.updateSalaryById(id,salary);
    }

    @GetMapping("/all-employee-detail")
    public Map getAllEmployeeDetails(){
        return employeeService.getAllEmployeeDetails();
    }

    @DeleteMapping("/delete-multiple-employee")
    public String deleteMultipleEmployee(@RequestBody EmployeeIdRequestDto employeeList){
        return employeeService.deleteEmployee(employeeList);
    }


}

        //TODO: create an api to update the salary only (done)
        //TODO: create an API to get all the employee data {hint: use employeeRepository to get all data, use finaAll() method}
        //TODO: Create a company model, controller, service dto, create(save), update, delete, find all company detail


        //TODO: Create an API to delete multiple user at a time.
        //TODO: