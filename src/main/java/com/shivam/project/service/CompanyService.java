package com.shivam.project.service;

import com.shivam.project.dto.CompanyIdRequestDto;
import com.shivam.project.dto.CompanyRequestDto;
import com.shivam.project.dto.CompanyResponseDto;
import com.shivam.project.model.Company;
import com.shivam.project.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyService {

    public final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Map save(CompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setName(companyRequestDto.getName());
        company.setDepartment(companyRequestDto.getDepartment());
        company.setContact(companyRequestDto.getContact());
        company.setRevenue(companyRequestDto.getRevenue());
        Company savedCompany = companyRepository.save(company);
        return companyResponse(savedCompany);
    }

    public Map companyResponse(Company savedCompany) {
        Map returnmap = new HashMap();
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        companyResponseDto.setId(savedCompany.getId());
        companyResponseDto.setName(savedCompany.getName());
        companyResponseDto.setDepartment(savedCompany.getDepartment());
        companyResponseDto.setContact(savedCompany.getContact());
        companyResponseDto.setRevenue(savedCompany.getRevenue());
        returnmap.put("Data", companyResponseDto);
        return returnmap;

    }

    public Company getCompanyById(Long id) {
        try {
            Optional<Company> optionalCompany = companyRepository.findById(id);
            if (!optionalCompany.isEmpty()) return optionalCompany.get();
            return new Company();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deleteCompanyById(Long id) {
        try {
            companyRepository.deleteById(id);
            return "Successfully Deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete or not Found";
        }
    }

    public Map updateCompanyById(Long id, CompanyRequestDto companyRequestDto) {
        Map map = new HashMap();
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company;
        if (optionalCompany.isEmpty()) {
            map.put("Message", "Not found");
            return map;
        }
        company = optionalCompany.get();
        company.setName(companyRequestDto.getName());
        company.setContact(companyRequestDto.getContact());
        company.setDepartment(companyRequestDto.getDepartment());
        company.setRevenue(companyRequestDto.getRevenue());
        Company company1 = companyRepository.save(company);
        map.put("Data", company1);
        return map;
    }

    public Map getCompaniesDetails() {
        List<Company> list = companyRepository.findAll();
        Map map = new HashMap();
        map.put("Data", list);
        return map;
    }

    public String deleteMultiplesCompanies(CompanyIdRequestDto companyIdRequestDto) {
        try {
            List<Long> companyList = companyIdRequestDto.getCompanyIdList();
            for (int i = 0; i < companyList.size(); i++) {
                companyRepository.deleteById(companyList.get(i));
            }
            return "Successfully Deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "Data not Found";
        }

    }
}