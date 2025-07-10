package com.learnspringboot.employeemanagement.employee_management_service.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
