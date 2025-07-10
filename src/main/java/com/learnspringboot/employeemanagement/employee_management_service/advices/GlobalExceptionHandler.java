package com.learnspringboot.employeemanagement.employee_management_service.advices;

import com.learnspringboot.employeemanagement.employee_management_service.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiError> handleEmployeeNotFoundException(EmployeeNotFoundException e){
        ApiError apiError = ApiError.builder()
                                    .message(e.getMessage())
                                    .status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllTypesOfException(Exception e){
        ApiError apiError = ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationError(MethodArgumentNotValidException e){
        List<String> subErrorsList = e.getBindingResult().getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList();
        ApiError apiError = ApiError.builder()
                .message("Input Validation Error")
                .status(HttpStatus.BAD_REQUEST)
                .subErrors(subErrorsList)
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
