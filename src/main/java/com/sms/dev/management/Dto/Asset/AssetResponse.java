package com.sms.dev.management.Dto.Asset;

import com.sms.dev.management.Entity.AssetStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetResponse {

    private Long id;

    private String name;

    private String type;

    private String serialNumber;

    private AssetStatus status;
}