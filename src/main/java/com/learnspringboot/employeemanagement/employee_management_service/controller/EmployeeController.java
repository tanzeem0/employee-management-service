package com.learnspringboot.employeemanagement.employee_management_service.controller;

import com.learnspringboot.employeemanagement.employee_management_service.dto.EmployeeDto;
import com.learnspringboot.employeemanagement.employee_management_service.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping(path = "/{empId}")
    public EmployeeDto getEmployeeById(@PathVariable Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false) Integer age){
        return employeeService.getAllEmployees(age);
    }

    @PostMapping
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto employeeDto)
    {
        return employeeService.createNewEmployee(employeeDto);
    }
}
