package com.learnspringboot.employeemanagement.employee_management_service.services;

import com.learnspringboot.employeemanagement.employee_management_service.dto.EmployeeDto;
import com.learnspringboot.employeemanagement.employee_management_service.entities.EmployeeEntity;
import com.learnspringboot.employeemanagement.employee_management_service.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeDto getEmployeeById(Long id){
        EmployeeEntity getEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(getEntity,EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployees(Integer age) {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .toList();
    }


    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity,EmployeeDto.class);
    }
}
