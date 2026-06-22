package com.sms.dev.management.Service;

import com.sms.dev.management.Dto.Assignment.AssetAssignmentResponse;
import com.sms.dev.management.Dto.Assignment.AssignAssetRequest;

import java.util.List;

public interface AssetAssignmentService {

    AssetAssignmentResponse assignAsset(
            AssignAssetRequest request
    );

    AssetAssignmentResponse returnAsset(
            Long assignmentId
    );

    List<AssetAssignmentResponse> getAssignments();
}