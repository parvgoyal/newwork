package com.parv.workflow.entity;

import com.parv.workflow.enums.DataStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "workflow_level_data_item")
public class WorkflowLevelDataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private WorkflowLevel level;

    @Column(name = "data_id")
    private String dataId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DataStatus status;

    @Column(name = "reason")
    private String reason;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WorkflowLevel getLevel() {
        return level;
    }

    public void setLevel(WorkflowLevel level) {
        this.level = level;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
