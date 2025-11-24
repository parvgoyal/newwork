package com.parv.workflow.mapper;

import com.parv.workflow.dto.*;
import com.parv.workflow.entity.*;
import com.parv.workflow.enums.ApprovalCondition;
import com.parv.workflow.enums.DataStatus;
import com.parv.workflow.enums.FlowType;
import com.parv.workflow.enums.WorkflowStatus;
import com.parv.workflow.enums.WorkflowType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkflowMapper {

    // --------------------------
    // convert Entity -> Response
    // --------------------------
    public WorkflowResponseDto toDto(Workflow workflow) {
        WorkflowResponseDto dto = new WorkflowResponseDto();
        dto.setWorkflowId(workflow.getId().toString());
        dto.setEntityId(workflow.getEntity().getId().toString());
        dto.setWorkflowType(workflow.getWorkflowType().name());
        dto.setStatus(workflow.getStatus() != null ? workflow.getStatus().name() : null);
        dto.setCurrentLevel(workflow.getCurrentLevel());

        // IMPORTANT: return exact flowType from request (flowTypeRaw)
        dto.setFlowType(workflow.getFlowTypeRaw());

        if (workflow.getWorkflowType() == WorkflowType.HIERARCHICAL) {
            dto.setLevels(
                    workflow.getLevels().stream()
                            .map(this::toLevelDto)
                            .collect(Collectors.toList())
            );
            dto.setDataIds(null);
        } else if (workflow.getWorkflowType() == WorkflowType.PARALLEL) {
            dto.setLevels(null);
            dto.setDataIds(
                    workflow.getDataItems().stream()
                            .map(this::toDataItemDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // --------------------------
    // Level -> DTO
    // --------------------------
    private WorkflowLevelDto toLevelDto(WorkflowLevel level) {
        WorkflowLevelDto dto = new WorkflowLevelDto();
        dto.setLevelNumber(level.getLevelNumber());
        dto.setStatus(level.getStatus() != null ? level.getStatus().name() : null);
        dto.setApprovalCondition(level.getApprovalCondition() != null ? level.getApprovalCondition().name() : null);
        dto.setDataType(level.getDataType());

        dto.setDataIds(
                level.getDataItems().stream()
                        .map(this::toLevelDataItemDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    private WorkflowLevelDataItemDto toLevelDataItemDto(WorkflowLevelDataItem item) {
        WorkflowLevelDataItemDto dto = new WorkflowLevelDataItemDto();
        dto.setDataId(item.getDataId());
        dto.setStatus(item.getStatus() != null ? item.getStatus().name().toLowerCase() : null);
        dto.setReason(item.getReason());
        return dto;
    }

    private WorkflowDataItemDto toDataItemDto(WorkflowDataItem item) {
        WorkflowDataItemDto dto = new WorkflowDataItemDto();
        dto.setDataId(item.getDataId());
        dto.setStatus(item.getStatus() != null ? item.getStatus().name().toLowerCase() : null);
        dto.setReason(item.getReason());
        return dto;
    }

    // --------------------------
    // Request Mapping Helpers
    // --------------------------
    public WorkflowStatus mapWorkflowStatus(String status) {
        return status == null ? null : WorkflowStatus.valueOf(status.toUpperCase());
    }

    public ApprovalCondition mapApprovalCondition(String cond) {
        return cond == null ? null : ApprovalCondition.valueOf(cond.toUpperCase());
    }

    public DataStatus mapDataStatus(String status) {
        if (status == null) {
            return DataStatus.PENDING;
        }
        return DataStatus.valueOf(status.toUpperCase());
    }

    public FlowType mapFlowType(String flow) {
        return flow == null ? null : FlowType.valueOf(flow.toUpperCase());
    }
}
