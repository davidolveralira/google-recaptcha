package com.dolvera.spring.google.services;

import com.dolvera.spring.google.models.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeEntity> findAll();
    EmployeeEntity findById(Long id);
    void createEmployee(EmployeeEntity employeeEntity);
    void deleteEmployeeById(Long id);
}
