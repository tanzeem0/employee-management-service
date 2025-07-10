package com.learnspringboot.employeemanagement.employee_management_service.controller;

import com.learnspringboot.employeemanagement.employee_management_service.dto.EmployeeDto;
import com.learnspringboot.employeemanagement.employee_management_service.exceptions.EmployeeNotFoundException;
import com.learnspringboot.employeemanagement.employee_management_service.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long empId){

        Optional<EmployeeDto> foundEmployee =  employeeService.getEmployeeById(empId);
        return foundEmployee.map(employeeDto -> ResponseEntity.ok(employeeDto)).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found: "+empId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false) Integer age){
        return  ResponseEntity.ok(employeeService.getAllEmployees(age));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody @Valid EmployeeDto employeeDto)
    {
        return new ResponseEntity<>(employeeService.createNewEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDto,employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean result = employeeService.deleteEmployeeById(employeeId);
        if(!result) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(true);
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@PathVariable Long employeeId, Map<String,Object> updates){
        EmployeeDto updatedEmployeeDto =  employeeService.updatePartialEmployeeById(employeeId,updates);
        if(updatedEmployeeDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedEmployeeDto);
    }
}
