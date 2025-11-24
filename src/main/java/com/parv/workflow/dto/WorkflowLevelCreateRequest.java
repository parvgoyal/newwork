package com.parv.workflow.dto;

import java.util.List;

public class WorkflowLevelCreateRequest {

    private Integer levelNumber;
    private String status;             // "PENDING", "NOT_STARTED"
    private String approvalCondition;  // "ALL", "ANY"
    private Integer dataType;
    private List<WorkflowLevelDataItemCreateRequest> dataIds;

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<WorkflowLevelDataItemCreateRequest> getDataIds() {
        return dataIds;
    }

    public void setDataIds(List<WorkflowLevelDataItemCreateRequest> dataIds) {
        this.dataIds = dataIds;
    }
}
