package com.learnspringboot.employeemanagement.employee_management_service.controller;

import com.learnspringboot.employeemanagement.employee_management_service.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(name = "/employees")
public class EmployeeController {

    @GetMapping(path = "/{empId}")
    public EmployeeDto getEmployeeById(@PathVariable Long empId){
        return new EmployeeDto(empId,"tanzeem","tanzeem@email",20,LocalDate.of(2022,Month.SEPTEMBER,12),true);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false) Integer age){
        return new ArrayList<>();
    }
}
