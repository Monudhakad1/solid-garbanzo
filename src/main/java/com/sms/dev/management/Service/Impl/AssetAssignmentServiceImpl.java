package com.sms.dev.management.Service.Impl;

import com.sms.dev.management.Dto.Assignment.AssetAssignmentResponse;
import com.sms.dev.management.Dto.Assignment.AssignAssetRequest;
import com.sms.dev.management.Entity.Asset;
import com.sms.dev.management.Entity.AssetAssignment;
import com.sms.dev.management.Entity.AssetStatus;
import com.sms.dev.management.Entity.Employee;
import com.sms.dev.management.Exception.AssetAlreadyAssignedException;
import com.sms.dev.management.Exception.ResourceNotFoundException;
import com.sms.dev.management.Repository.AssetAssignmentRepository;
import com.sms.dev.management.Repository.AssetRepository;
import com.sms.dev.management.Repository.EmployeeRepository;
import com.sms.dev.management.Service.AssetAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetAssignmentServiceImpl
        implements AssetAssignmentService {

    private final EmployeeRepository employeeRepository;

    private final AssetRepository assetRepository;

    private final AssetAssignmentRepository assetAssignmentRepository;

    @Override
    public AssetAssignmentResponse assignAsset(
            AssignAssetRequest request
    ) {

        Employee employee = employeeRepository.findById(
                        request.getEmployeeId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"
                        ));

        Asset asset = assetRepository.findById(
                        request.getAssetId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Asset not found"
                        ));

        if (asset.getStatus() == AssetStatus.ASSIGNED) {

            throw new AssetAlreadyAssignedException(
                    "Asset already assigned"
            );
        }

        asset.setStatus(AssetStatus.ASSIGNED);

        assetRepository.save(asset);

        AssetAssignment assignment =
                AssetAssignment.builder()

                        .employee(employee)

                        .asset(asset)

                        .assignedDate(LocalDate.now())

                        .build();

        AssetAssignment saved =
                assetAssignmentRepository.save(
                        assignment
                );

        return mapToResponse(saved);
    }

    @Override
    public AssetAssignmentResponse returnAsset(
            Long assignmentId
    ) {

        AssetAssignment assignment =
                assetAssignmentRepository.findById(
                                assignmentId
                        )

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Assignment not found"
                                ));

        assignment.setReturnedDate(
                LocalDate.now()
        );

        Asset asset = assignment.getAsset();

        asset.setStatus(
                AssetStatus.AVAILABLE
        );

        assetRepository.save(asset);

        AssetAssignment updated =
                assetAssignmentRepository.save(
                        assignment
                );

        return mapToResponse(updated);
    }

    @Override
    public List<AssetAssignmentResponse>
    getAssignments() {

        return assetAssignmentRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();
    }

    private AssetAssignmentResponse mapToResponse(
            AssetAssignment assignment
    ) {

        return AssetAssignmentResponse.builder()

                .id(assignment.getId())

                .employeeName(
                        assignment.getEmployee().getName()
                )

                .assetName(
                        assignment.getAsset().getName()
                )

                .assignedDate(
                        assignment.getAssignedDate()
                )

                .returnedDate(
                        assignment.getReturnedDate()
                )

                .build();
    }
}