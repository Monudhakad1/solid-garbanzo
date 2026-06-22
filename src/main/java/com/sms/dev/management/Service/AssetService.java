package com.sms.dev.management.Service;

import com.sms.dev.management.Dto.Asset.AssetResponse;
import com.sms.dev.management.Dto.Asset.CreateAssetRequest;
import com.sms.dev.management.Dto.Asset.UpdateAssetRequest;

import java.util.List;

public interface AssetService {

    AssetResponse createAsset(CreateAssetRequest request);

    List<AssetResponse> getAssets();

    AssetResponse getAsset(Long id);

    AssetResponse updateAsset(Long id,
                              UpdateAssetRequest request);

    void deleteAsset(Long id);
}