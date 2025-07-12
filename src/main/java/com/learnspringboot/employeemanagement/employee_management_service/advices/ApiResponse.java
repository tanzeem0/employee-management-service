package com.learnspringboot.employeemanagement.employee_management_service.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime localDateTime;
    private T data;
    private ApiError error;

    public ApiResponse(){
        localDateTime = LocalDateTime.now();
    }
    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }
}
