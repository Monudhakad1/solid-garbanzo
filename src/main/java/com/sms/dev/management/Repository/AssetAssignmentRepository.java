package com.sms.dev.management.Repository;
import com.sms.dev.management.Entity.AssetAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment, Long> {

    List<AssetAssignment> findByEmployeeId(Long employeeId);

    List<AssetAssignment> findByAssetId(Long assetId);

//    Optional<AssetAssignment> findById(Long id);
//
}