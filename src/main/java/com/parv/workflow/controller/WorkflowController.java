package com.parv.workflow.controller;

import com.parv.workflow.dto.WorkflowCreateRequest;
import com.parv.workflow.dto.WorkflowResponseDto;
import com.parv.workflow.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping
    public WorkflowResponseDto createWorkflow(@RequestBody WorkflowCreateRequest request) {
        return workflowService.createWorkflow(request);
    }

    @GetMapping("/{id}")
    public WorkflowResponseDto getWorkflow(@PathVariable("id") UUID id) {
        return workflowService.getWorkflowById(id);
    }
}
