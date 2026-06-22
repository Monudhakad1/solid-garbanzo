package com.sms.dev.management.Controller;

import com.sms.dev.management.Dto.Asset.AssetResponse;
import com.sms.dev.management.Dto.Asset.CreateAssetRequest;
import com.sms.dev.management.Dto.Asset.UpdateAssetRequest;
import com.sms.dev.management.Service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetResponse>
    createAsset(

            @Valid
            @RequestBody
            CreateAssetRequest request
    ) {

        return ResponseEntity.ok(
                assetService.createAsset(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<AssetResponse>>
    getAssets() {

        return ResponseEntity.ok(
                assetService.getAssets()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponse>
    getAsset(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                assetService.getAsset(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetResponse>
    updateAsset(

            @PathVariable Long id,

            @Valid
            @RequestBody
            UpdateAssetRequest request
    ) {

        return ResponseEntity.ok(
                assetService.updateAsset(
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteAsset(
            @PathVariable Long id
    ) {

        assetService.deleteAsset(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}