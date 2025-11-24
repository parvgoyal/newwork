package com.parv.workflow.service;

import com.parv.workflow.dto.WorkflowCreateRequest;
import com.parv.workflow.dto.WorkflowResponseDto;

import java.util.UUID;

public interface WorkflowService {

    WorkflowResponseDto createWorkflow(WorkflowCreateRequest request);

    WorkflowResponseDto getWorkflowById(UUID id);
}
