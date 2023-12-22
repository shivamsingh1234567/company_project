package com.shivam.project.controllers;

import com.shivam.project.dto.CompanyIdRequestDto;
import com.shivam.project.dto.CompanyRequestDto;
import com.shivam.project.model.Company;
import com.shivam.project.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v2")
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/create")
    public Map save(@RequestBody CompanyRequestDto companyRequestDto){
        return companyService.save(companyRequestDto);
    }

    @GetMapping("/get-by-id/{id}")
    public Company getCompanyById(@PathVariable("id") Long id){
        return companyService.getCompanyById(id);
    }

    @DeleteMapping("/delete-company-by-id/{id}")
    public String deleteCompanyById(@PathVariable("id") Long id){
        return companyService.deleteCompanyById(id);
    }

    @PutMapping("/update-company-by-id/{id}")
    public Map updateCompanyById(@PathVariable("id") Long id, @RequestBody CompanyRequestDto companyRequestDto){
        return companyService.updateCompanyById(id,companyRequestDto);
    }

    @GetMapping("/get-all-company-details")
    public Map getCompaniesDetail(){
        return companyService.getCompaniesDetails();
    }

    @DeleteMapping("/delete-multiples-companies")
    public String deleteMultipleCompanies(@RequestBody CompanyIdRequestDto companyIdRequestDto){
        return companyService.deleteMultiplesCompanies(companyIdRequestDto);
    }


}
