package com.sms.dev.management.Controller;

import com.sms.dev.management.Dto.Assignment.AssetAssignmentResponse;
import com.sms.dev.management.Dto.Assignment.AssignAssetRequest;
import com.sms.dev.management.Service.AssetAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssetAssignmentController {

    private final AssetAssignmentService
            assetAssignmentService;

    @PostMapping
    public ResponseEntity<AssetAssignmentResponse>
    assignAsset(

            @Valid
            @RequestBody
            AssignAssetRequest request
    ) {

        return ResponseEntity.ok(

                assetAssignmentService
                        .assignAsset(request)

        );
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<AssetAssignmentResponse>
    returnAsset(

            @PathVariable Long id
    ) {

        return ResponseEntity.ok(

                assetAssignmentService
                        .returnAsset(id)

        );
    }

    @GetMapping
    public ResponseEntity<List<
            AssetAssignmentResponse>>
    getAssignments() {

        return ResponseEntity.ok(

                assetAssignmentService
                        .getAssignments()

        );
    }
}