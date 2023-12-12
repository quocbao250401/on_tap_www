package com.example.www_on_tap.service;

import com.example.www_on_tap.entity.Employee;
import com.example.www_on_tap.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public boolean deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
        return true;
    }

    public Employee updateEmployee(Employee employee){
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        existingEmployee.setFull_name(employee.getFull_name());
        existingEmployee.setDob(employee.getDob());
        existingEmployee.setStatus(employee.getStatus());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        return employeeRepository.save(existingEmployee);
    }
}
