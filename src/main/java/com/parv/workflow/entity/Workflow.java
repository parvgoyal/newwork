package com.parv.workflow.entity;

import com.parv.workflow.enums.FlowType;
import com.parv.workflow.enums.WorkflowStatus;
import com.parv.workflow.enums.WorkflowType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workflow")
public class Workflow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    private BusinessEntity entity;

    @Enumerated(EnumType.STRING)
    @Column(name = "workflow_type")
    private WorkflowType workflowType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WorkflowStatus status;

    @Column(name = "current_level")
    private Integer currentLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "flow_type")
    private FlowType flowType;

    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkflowLevel> levels = new ArrayList<>();

    @OneToMany(mappedBy = "workflow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkflowDataItem> dataItems = new ArrayList<>();

    @Column(name = "flow_type_raw")
    private String flowTypeRaw;

    public String getFlowTypeRaw() {
        return flowTypeRaw;
    }

    public void setFlowTypeRaw(String flowTypeRaw) {
        this.flowTypeRaw = flowTypeRaw;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BusinessEntity getEntity() {
        return entity;
    }

    public void setEntity(BusinessEntity entity) {
        this.entity = entity;
    }

    public WorkflowType getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(WorkflowType workflowType) {
        this.workflowType = workflowType;
    }

    public WorkflowStatus getStatus() {
        return status;
    }

    public void setStatus(WorkflowStatus status) {
        this.status = status;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public List<WorkflowLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<WorkflowLevel> levels) {
        this.levels = levels;
    }

    public List<WorkflowDataItem> getDataItems() {
        return dataItems;
    }

    public void setDataItems(List<WorkflowDataItem> dataItems) {
        this.dataItems = dataItems;
    }
}
