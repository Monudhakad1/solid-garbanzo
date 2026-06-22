package com.sms.dev.management.Dto.Assignment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignAssetRequest {

    @NotNull(message = "Employee id is required")
    private Long employeeId;

    @NotNull(message = "Asset id is required")
    private Long assetId;
}