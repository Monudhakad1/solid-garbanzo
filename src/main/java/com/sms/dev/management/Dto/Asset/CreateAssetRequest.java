package com.sms.dev.management.Dto.Asset;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAssetRequest {

    @NotBlank(message = "Asset name is required")
    private String name;

    @NotBlank(message = "Asset type is required")
    private String type;

    @NotBlank(message = "Serial number is required")
    private String serialNumber;
}