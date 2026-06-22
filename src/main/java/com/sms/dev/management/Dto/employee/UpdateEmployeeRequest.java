package com.sms.dev.management.Dto.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;
}
