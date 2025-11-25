package com.parv.workflow.controller;

import com.parv.workflow.dto.*;
import com.parv.workflow.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping
    public WorkflowResponseDto createWorkflow(@RequestBody WorkflowCreateRequest req) {
        return workflowService.createWorkflow(req);
    }

    @GetMapping("/{id}")
    public WorkflowResponseDto getWorkflow(@PathVariable UUID id) {
        return workflowService.getWorkflowById(id);
    }

    @PutMapping("/{id}")
    public WorkflowResponseDto updateWorkflow(
            @PathVariable UUID id,
            @RequestBody WorkflowUpdateRequest req
    ) {
        return workflowService.updateWorkflow(id, req);
    }


    @GetMapping("/reviewer")
    public List<WorkflowResponseDto> getReviewer() {
        return workflowService.getReviewerWorkflows();
    }

    @GetMapping("/assignee")
    public List<WorkflowResponseDto> getAssignee() {
        return workflowService.getAssigneeWorkflows();
    }

    @PutMapping("/reviewer/{id}")
    public WorkflowResponseDto updateReviewer(
            @PathVariable UUID id,
            @RequestBody WorkflowUpdateRequest req
    ) {
        return workflowService.updateReviewerWorkflow(id, req);
    }

    @PutMapping("/assignee/{id}")
    public WorkflowResponseDto updateAssignee(
            @PathVariable UUID id,
            @RequestBody WorkflowUpdateRequest req
    ) {
        return workflowService.updateAssigneeWorkflow(id, req);
    }
}
