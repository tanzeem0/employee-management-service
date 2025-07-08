package com.learnspringboot.employeemanagement.employee_management_service.dto;

import com.learnspringboot.employeemanagement.employee_management_service.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in the range: [3,10]")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Max(value = 50, message = "The age of the employee cannot be more than 60 years")
    @Min(value = 18, message = "The age of the employee cannot be less than 18 years")
    private Integer age;

    @NotNull
    @PastOrPresent(message = "Joining date of employee should not be in future")
    private LocalDate dateOfJoining;

    @NotBlank
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "The roles of the employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String roles;

    @NotNull(message = "salary of the employee cannot be null")
    @Positive(message = "salary of the employee should be positive")
    @Digits(integer = 100000, fraction = 99,message = "All employees must have below 6 figure salary")
    @DecimalMax(value = "100000.99",message = "salary should not exceed 1 Lakh")
    @DecimalMin(value = "100.50" , message = "minimum salary should not be below 100 Rupees")
    private Double salary;

    @AssertTrue(message = "The status of the employee should be active")
    private Boolean isActive;
}
