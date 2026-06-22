package com.sms.dev.management.Service;

import com.sms.dev.management.Dto.employee.CreateEmployeeRequest;
import com.sms.dev.management.Dto.employee.EmployeeResponse;
import com.sms.dev.management.Dto.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(CreateEmployeeRequest request);

    List<EmployeeResponse> getEmployees();

    EmployeeResponse getEmployee(Long id);

    EmployeeResponse updateEmployee(Long id,
                                    UpdateEmployeeRequest request);

    void deleteEmployee(Long id);
}