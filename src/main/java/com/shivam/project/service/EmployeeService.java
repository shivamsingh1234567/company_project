package com.shivam.project.service;

import com.shivam.project.dto.EmployeeIdRequestDto;
import com.shivam.project.dto.EmployeeRequestDto;
import com.shivam.project.dto.EmployeeResponseDto;
import com.shivam.project.model.Employee;
import com.shivam.project.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Map save(EmployeeRequestDto employeeRequestDto){
        Employee employee=new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setContact(employeeRequestDto.getContact());
        employee.setSalary(employeeRequestDto.getSalary());
        employee.setDepartment(employeeRequestDto.getDepartment());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeResponse(savedEmployee);
    }

    public Map employeeResponse(Employee savedEmployee){
        Map returnMap = new HashMap();
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setId(savedEmployee.getId());
        employeeResponseDto.setName(savedEmployee.getName());
        employeeResponseDto.setContact(savedEmployee.getContact());
        employeeResponseDto.setDepartment(savedEmployee.getDepartment());
        employeeResponseDto.setSalary(savedEmployee.getSalary());
        returnMap.put("Data", employeeResponseDto);
        return returnMap;
    }

    public Employee getEmployeeById(Long id){
        try{
            Optional<Employee> optionalEmployee =  employeeRepository.findById(id);
            if(!optionalEmployee.isEmpty()) return optionalEmployee.get();
            return new Employee();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String deleteEmployeeById(Long id) {
        try{
            employeeRepository.deleteById(id);
            return "successfully deleted";
        }catch (Exception e){
            e.printStackTrace();
            return "failed to delete or not found";
        }
    }

    public Map updateEmployeeById(Long id, EmployeeRequestDto employeeRequestDto){
        Map map = new HashMap();
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee;
        if (optionalEmployee.isEmpty()){
            map.put("Message","Data not found.");
            return map;
        }
        employee=optionalEmployee.get();
        employee.setName(employeeRequestDto.getName());
        employee.setContact(employeeRequestDto.getContact());
        employee.setDepartment(employeeRequestDto.getDepartment());
        employee.setSalary(employeeRequestDto.getSalary());
        Employee employee1 = employeeRepository.save(employee);
        map.put("Data", employee1);
        return map;
    }


    public Map updateSalaryById(Long id, Float salary){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Map map = new HashMap();
        if (optionalEmployee.isEmpty()){
            map.put("Message","Value is not found");
            return map;
        }
        Employee employee = optionalEmployee.get();
        employee.setSalary(salary);
        Employee employee1 =employeeRepository.save(employee);
        map.put("Data", employee1);
        return map;
    }

    public Map getAllEmployeeDetails(){
        List<Employee> employeeList =  employeeRepository.findAll();
        Map employeeMap = new HashMap();
        employeeMap.put("Employee_Data", employeeList);
        return employeeMap;
    }

    public String deleteEmployee(EmployeeIdRequestDto employeeIdRequestDto){
        try {
            List<Long> companyList = employeeIdRequestDto.getEmployeeIdList();
            for(int i=0; i<companyList.size(); i++){
            employeeRepository.deleteById(companyList.get(i));
            }
            return "Successfully deleted user.";
        } catch(Exception e){
            e.printStackTrace();
            return "Filed to delete user";
        }
    }





}
