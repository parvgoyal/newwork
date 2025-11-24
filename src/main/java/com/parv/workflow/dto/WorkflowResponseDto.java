package com.parv.workflow.dto;

import java.util.List;

public class WorkflowResponseDto {

    private String workflowId;
    private String entityId;
    private String workflowType;
    private String status;
    private Integer currentLevel;
    private String flowType;
    private List<WorkflowLevelDto> levels;    // for HIERARCHICAL
    private List<WorkflowDataItemDto> dataIds; // for PARALLEL

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<WorkflowDataItemDto> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<WorkflowDataItemDto> dataIds) {
        this.dataIds = dataIds;
    }
}
