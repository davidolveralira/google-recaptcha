package com.dolvera.spring.google.services;

import com.dolvera.spring.google.models.entity.EmployeeEntity;
import com.dolvera.spring.google.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<EmployeeEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public EmployeeEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void createEmployee(EmployeeEntity employeeEntity) {
        repository.save(employeeEntity);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }
}
