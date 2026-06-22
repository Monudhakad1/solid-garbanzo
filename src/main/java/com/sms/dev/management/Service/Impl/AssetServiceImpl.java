package com.sms.dev.management.Service.Impl;

import com.sms.dev.management.Dto.Asset.AssetResponse;
import com.sms.dev.management.Dto.Asset.CreateAssetRequest;
import com.sms.dev.management.Dto.Asset.UpdateAssetRequest;
import com.sms.dev.management.Entity.Asset;
import com.sms.dev.management.Exception.DuplicateResourceException;
import com.sms.dev.management.Exception.ResourceNotFoundException;
import com.sms.dev.management.Repository.AssetRepository;
import com.sms.dev.management.Service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public AssetResponse createAsset(CreateAssetRequest request) {

        if (assetRepository.existsBySerialNumber(
                request.getSerialNumber()
        )) {

            throw new DuplicateResourceException(
                    "Serial number already exists"
            );
        }

        Asset asset = Asset.builder()
                .name(request.getName())
                .type(request.getType())
                .serialNumber(request.getSerialNumber())
                .build();

        Asset saved = assetRepository.save(asset);

        return mapToResponse(saved);
    }

    @Override
    public List<AssetResponse> getAssets() {

        return assetRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AssetResponse getAsset(Long id) {

        Asset asset = assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Asset not found"
                        ));

        return mapToResponse(asset);
    }

    @Override
    public AssetResponse updateAsset(Long id,
                                     UpdateAssetRequest request) {

        Asset asset = assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Asset not found"
                        ));

        asset.setName(request.getName());

        asset.setType(request.getType());

        Asset updated = assetRepository.save(asset);

        return mapToResponse(updated);
    }

    @Override
    public void deleteAsset(Long id) {

        Asset asset = assetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Asset not found"
                        ));

        assetRepository.delete(asset);
    }

    private AssetResponse mapToResponse(
            Asset asset
    ) {

        return AssetResponse.builder()
                .id(asset.getId())
                .name(asset.getName())
                .type(asset.getType())
                .serialNumber(asset.getSerialNumber())
                .status(asset.getStatus())
                .build();
    }
}