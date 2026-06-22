package com.sms.dev.management.Dto.Assignment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AssetAssignmentResponse {

    private Long id;

    private String employeeName;

    private String assetName;

    private LocalDate assignedDate;

    private LocalDate returnedDate;
}
