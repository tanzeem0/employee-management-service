package com.learnspringboot.employeemanagement.employee_management_service.repositories;

import com.learnspringboot.employeemanagement.employee_management_service.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
