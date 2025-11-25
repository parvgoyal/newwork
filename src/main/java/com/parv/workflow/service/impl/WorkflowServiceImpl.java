package com.parv.workflow.service.impl;

import com.parv.workflow.dto.*;
import com.parv.workflow.entity.*;
import com.parv.workflow.enums.DataStatus;
import com.parv.workflow.enums.FlowType;
import com.parv.workflow.enums.WorkflowStatus;
import com.parv.workflow.enums.WorkflowType;
import com.parv.workflow.mapper.WorkflowMapper;
import com.parv.workflow.repository.BusinessEntityRepository;
import com.parv.workflow.repository.WorkflowRepository;
import com.parv.workflow.service.WorkflowService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowRepository workflowRepository;
    private final BusinessEntityRepository businessEntityRepository;
    private final WorkflowMapper workflowMapper;

    public WorkflowServiceImpl(
            WorkflowRepository workflowRepository,
            BusinessEntityRepository businessEntityRepository,
            WorkflowMapper workflowMapper
    ) {
        this.workflowRepository = workflowRepository;
        this.businessEntityRepository = businessEntityRepository;
        this.workflowMapper = workflowMapper;
    }

    // ---------------------------------------------------
    // CREATE WORKFLOW
    // ---------------------------------------------------
    @Override
    public WorkflowResponseDto createWorkflow(WorkflowCreateRequest request) {

        if (request.getEntityId() == null) {
            throw new RuntimeException("entityId is required");
        }

        UUID entityId = UUID.fromString(request.getEntityId());
        BusinessEntity entity = businessEntityRepository.findById(entityId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        String type = request.getWorkflowType().trim().toUpperCase();

        Workflow workflow = new Workflow();
        workflow.setEntity(entity);
        workflow.setStatus(WorkflowStatus.PENDING);
        workflow.setFlowType(workflowMapper.mapFlowType(request.getFlowType()));
        workflow.setFlowTypeRaw(request.getFlowType());

        // HIERARCHICAL
        if (type.equals("HIERARCHICAL")) {

            workflow.setWorkflowType(WorkflowType.HIERARCHICAL);
            workflow.setCurrentLevel(request.getCurrentLevel());

            List<WorkflowLevel> levels = new ArrayList<>();

            if (request.getLevels() != null) {
                for (WorkflowLevelCreateRequest levelReq : request.getLevels()) {

                    WorkflowLevel level = new WorkflowLevel();
                    level.setWorkflow(workflow);
                    level.setLevelNumber(levelReq.getLevelNumber());
                    level.setStatus(workflowMapper.mapWorkflowStatus(levelReq.getStatus()));
                    level.setApprovalCondition(workflowMapper.mapApprovalCondition(levelReq.getApprovalCondition()));
                    level.setDataType(levelReq.getDataType());

                    List<WorkflowLevelDataItem> items = new ArrayList<>();

                    if (levelReq.getDataIds() != null) {
                        for (WorkflowLevelDataItemCreateRequest d : levelReq.getDataIds()) {
                            WorkflowLevelDataItem item = new WorkflowLevelDataItem();
                            item.setLevel(level);
                            item.setDataId(d.getDataId());
                            item.setStatus(workflowMapper.mapDataStatus(d.getStatus()));
                            item.setReason(d.getReason());
                            items.add(item);
                        }
                    }

                    level.getDataItems().addAll(items);
                    levels.add(level);
                }
            }

            workflow.getLevels().addAll(levels);
            workflow.setDataItems(new ArrayList<>());
        }

        // PARALLEL
        else if (type.equals("PARALLEL")) {

            workflow.setWorkflowType(WorkflowType.PARALLEL);
            workflow.setCurrentLevel(null);
            workflow.setLevels(new ArrayList<>());

            List<WorkflowDataItem> items = new ArrayList<>();

            if (request.getDataIds() != null) {
                for (WorkflowDataItemCreateRequest d : request.getDataIds()) {
                    WorkflowDataItem item = new WorkflowDataItem();
                    item.setWorkflow(workflow);
                    item.setDataId(d.getDataId());
                    item.setStatus(workflowMapper.mapDataStatus(d.getStatus()));
                    item.setReason(d.getReason());
                    items.add(item);
                }
            }

            workflow.getDataItems().addAll(items);
        }

        else {
            throw new RuntimeException("Invalid workflowType");
        }

        Workflow saved = workflowRepository.save(workflow);
        return workflowMapper.toDto(saved);
    }

    // ---------------------------------------------------
    // GET WORKFLOW
    // ---------------------------------------------------
    @Override
    public WorkflowResponseDto getWorkflowById(UUID id) {
        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
        return workflowMapper.toDto(workflow);
    }

    // ---------------------------------------------------
    // GENERIC UPDATE
    // ---------------------------------------------------
    @Override
    public WorkflowResponseDto updateWorkflow(UUID id, WorkflowUpdateRequest req) {

        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        if (req.getStatus() != null) {
            workflow.setStatus(workflowMapper.mapWorkflowStatus(req.getStatus()));
        }

        if (req.getFlowType() != null) {
            workflow.setFlowType(workflowMapper.mapFlowType(req.getFlowType()));
        }

        // HIERARCHICAL UPDATE
        if (workflow.getWorkflowType() == WorkflowType.HIERARCHICAL) {

            if (req.getCurrentLevel() != null) {
                workflow.setCurrentLevel(req.getCurrentLevel());
            }

            if (req.getLevels() != null) {
                workflow.getLevels().clear();

                for (WorkflowLevelCreateRequest levelReq : req.getLevels()) {

                    WorkflowLevel level = new WorkflowLevel();
                    level.setWorkflow(workflow);
                    level.setLevelNumber(levelReq.getLevelNumber());
                    level.setStatus(workflowMapper.mapWorkflowStatus(levelReq.getStatus()));
                    level.setApprovalCondition(workflowMapper.mapApprovalCondition(levelReq.getApprovalCondition()));
                    level.setDataType(levelReq.getDataType());

                    List<WorkflowLevelDataItem> items = new ArrayList<>();

                    if (levelReq.getDataIds() != null) {
                        for (WorkflowLevelDataItemCreateRequest d : levelReq.getDataIds()) {
                            WorkflowLevelDataItem item = new WorkflowLevelDataItem();
                            item.setLevel(level);
                            item.setDataId(d.getDataId());
                            item.setStatus(workflowMapper.mapDataStatus(d.getStatus()));
                            item.setReason(d.getReason());
                            items.add(item);
                        }
                    }

                    level.getDataItems().clear();
                    level.getDataItems().addAll(items);

                    workflow.getLevels().add(level);
                }
            }
        }

        // PARALLEL UPDATE
        else {

            if (req.getDataIds() != null) {

                workflow.getDataItems().clear();

                for (WorkflowDataItemCreateRequest d : req.getDataIds()) {
                    WorkflowDataItem item = new WorkflowDataItem();
                    item.setWorkflow(workflow);
                    item.setDataId(d.getDataId());
                    item.setStatus(workflowMapper.mapDataStatus(d.getStatus()));
                    item.setReason(d.getReason());
                    workflow.getDataItems().add(item);
                }
            }
        }

        Workflow saved = workflowRepository.save(workflow);
        return workflowMapper.toDto(saved);
    }

    // ---------------------------------------------------
    // ASSIGNEE GET
    // ---------------------------------------------------
    @Override
    public List<WorkflowResponseDto> getAssigneeWorkflows() {
        return workflowRepository.findByFlowType(FlowType.ASSIGNEE)
                .stream()
                .map(workflowMapper::toDto)
                .toList();
    }

    // ---------------------------------------------------
    // REVIEWER GET
    // ---------------------------------------------------
    @Override
    public List<WorkflowResponseDto> getReviewerWorkflows() {
        return workflowRepository.findByFlowType(FlowType.REVIEWER)
                .stream()
                .map(workflowMapper::toDto)
                .toList();
    }

    // ---------------------------------------------------
    // ASSIGNEE UPDATE
    // ---------------------------------------------------
    @Override
    public WorkflowResponseDto updateAssigneeWorkflow(UUID id, WorkflowUpdateRequest req) {
        req.setFlowType("ASSIGNEE");
        return updateWorkflow(id, req);
    }

    // ---------------------------------------------------
    // REVIEWER UPDATE
    // ---------------------------------------------------
    @Override
    public WorkflowResponseDto updateReviewerWorkflow(UUID id, WorkflowUpdateRequest req) {
        req.setFlowType("REVIEWER");
        return updateWorkflow(id, req);
    }
}
