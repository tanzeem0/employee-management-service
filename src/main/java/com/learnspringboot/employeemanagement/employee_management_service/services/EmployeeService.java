package com.learnspringboot.employeemanagement.employee_management_service.services;

import com.learnspringboot.employeemanagement.employee_management_service.dto.EmployeeDto;
import com.learnspringboot.employeemanagement.employee_management_service.entities.EmployeeEntity;
import com.learnspringboot.employeemanagement.employee_management_service.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public Optional<EmployeeDto> getEmployeeById(Long id){
        Optional<EmployeeEntity> getEntity = employeeRepository.findById(id);
        return getEntity.map(employeeEntity -> modelMapper.map(getEntity,EmployeeDto.class));
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

    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDto.class);
    }
    public boolean isExists(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isExists(employeeId);
        if(!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDto updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExists(employeeId);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field updatedField = ReflectionUtils.findRequiredField(employeeEntity.getClass(),field);
            updatedField.setAccessible(true);
            ReflectionUtils.setField(updatedField,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }
}
