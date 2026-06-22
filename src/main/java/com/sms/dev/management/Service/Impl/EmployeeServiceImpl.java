package com.sms.dev.management.Service.Impl;

import com.sms.dev.management.Dto.employee.CreateEmployeeRequest;
import com.sms.dev.management.Dto.employee.EmployeeResponse;
import com.sms.dev.management.Dto.employee.UpdateEmployeeRequest;
import com.sms.dev.management.Entity.Employee;
import com.sms.dev.management.Exception.DuplicateResourceException;
import com.sms.dev.management.Exception.ResourceNotFoundException;
import com.sms.dev.management.Repository.EmployeeRepository;
import com.sms.dev.management.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .build();

        Employee saved = employeeRepository.save(employee);

        return mapToResponse(saved);
    }

    @Override
    public List<EmployeeResponse> getEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id,
                                           UpdateEmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());

        Employee updated = employeeRepository.save(employee);

        return mapToResponse(updated);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {

        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .build();
    }
}