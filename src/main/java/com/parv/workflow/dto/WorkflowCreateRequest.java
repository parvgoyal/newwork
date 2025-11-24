package com.parv.workflow.dto;

import java.util.List;

public class WorkflowCreateRequest {

    private String entityId;
    private String workflowType; // <-- FIXED to STRING
    private String flowType;
    private Integer currentLevel;

    private List<WorkflowLevelCreateRequest> levels;
    private List<WorkflowDataItemCreateRequest> dataIds;

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

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public List<WorkflowLevelCreateRequest> getLevels() {
        return levels;
    }

    public void setLevels(List<WorkflowLevelCreateRequest> levels) {
        this.levels = levels;
    }

    public List<WorkflowDataItemCreateRequest> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<WorkflowDataItemCreateRequest> dataIds) {
        this.dataIds = dataIds;
    }
}
