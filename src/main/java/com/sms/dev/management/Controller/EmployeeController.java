package com.sms.dev.management.Controller;

import com.sms.dev.management.Dto.employee.CreateEmployeeRequest;
import com.sms.dev.management.Dto.employee.EmployeeResponse;
import com.sms.dev.management.Dto.employee.UpdateEmployeeRequest;
import com.sms.dev.management.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request
    ) {

        return ResponseEntity.ok(
                employeeService.createEmployee(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>>
    getEmployees() {

        return ResponseEntity.ok(
                employeeService.getEmployees()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse>
    getEmployee(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                employeeService.getEmployee(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse>
    updateEmployee(

            @PathVariable Long id,

            @Valid
            @RequestBody
            UpdateEmployeeRequest request
    ) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteEmployee(
            @PathVariable Long id
    ) {

        employeeService.deleteEmployee(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}