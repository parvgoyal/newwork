package com.parv.workflow.service;

import com.parv.workflow.dto.*;
import java.util.List;
import java.util.UUID;

public interface WorkflowService {

    WorkflowResponseDto createWorkflow(WorkflowCreateRequest request);

    WorkflowResponseDto getWorkflowById(UUID id);

    WorkflowResponseDto updateWorkflow(UUID id, WorkflowUpdateRequest request);

    // NEW
    List<WorkflowResponseDto> getReviewerWorkflows();
    List<WorkflowResponseDto> getAssigneeWorkflows();

    WorkflowResponseDto updateReviewerWorkflow(UUID id, WorkflowUpdateRequest request);
    WorkflowResponseDto updateAssigneeWorkflow(UUID id, WorkflowUpdateRequest request);
}
