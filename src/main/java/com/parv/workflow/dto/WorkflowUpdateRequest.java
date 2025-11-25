package com.parv.workflow.dto;

import com.parv.workflow.controller.WorkflowController;

import java.util.List;

public class WorkflowUpdateRequest {

    private String flowType;        // ASSIGNEE | REVIEWER
    private String status;          // PENDING | APPROVED | REJECTED
    private Integer currentLevel;   // only for hierarchical

    private List<WorkflowLevelCreateRequest> levels;   // hierarchical
    private List<WorkflowDataItemCreateRequest> dataIds; // parallel

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
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
