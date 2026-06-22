package com.sms.dev.management.Repository;

import com.sms.dev.management.Entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    boolean existsBySerialNumber(String serialNumber);

    Optional<Asset> findBySerialNumber(String serialNumber);
}