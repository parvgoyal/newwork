package com.parv.workflow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkflowDto {

    private String workflowId;
    private String entityId;
    private String workflowType;
    private Integer currentLevel;
    private String flowType;

    // hierarchical
    private List<WorkflowLevelDto> levels;

    // parallel
    private String status;
    private LocalDateTime completedAt;
    private String approvalCondition;
    private Integer dataType;
    private List<WorkflowDataItemDto> dataIds;

    public WorkflowDto() {
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public List<WorkflowLevelDto> getLevels() {
        return levels;
    }

    public void setLevels(List<WorkflowLevelDto> levels) {
        this.levels = levels;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getApprovalCondition() {
        return approvalCondition;
    }

    public void setApprovalCondition(String approvalCondition) {
        this.approvalCondition = approvalCondition;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<WorkflowDataItemDto> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<WorkflowDataItemDto> dataIds) {
        this.dataIds = dataIds;
    }
}
