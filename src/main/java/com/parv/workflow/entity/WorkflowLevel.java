package com.parv.workflow.entity;

import com.parv.workflow.enums.ApprovalCondition;
import com.parv.workflow.enums.WorkflowStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workflow_level")
public class WorkflowLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "workflow_id", nullable = false)
    private Workflow workflow;

    @Column(name = "level_number")
    private Integer levelNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WorkflowStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_condition")
    private ApprovalCondition approvalCondition;

    @Column(name = "data_type")
    private Integer dataType;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkflowLevelDataItem> dataItems = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public WorkflowStatus getStatus() {
        return status;
    }

    public void setStatus(WorkflowStatus status) {
        this.status = status;
    }

    public ApprovalCondition getApprovalCondition() {
        return approvalCondition;
    }

    public void setApprovalCondition(ApprovalCondition approvalCondition) {
        this.approvalCondition = approvalCondition;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<WorkflowLevelDataItem> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<WorkflowLevelDataItem> dataItems) {
        this.dataItems = dataItems;
    }
}
