package com.parv.workflow.service.impl;

import com.parv.workflow.dto.*;
import com.parv.workflow.entity.*;
import com.parv.workflow.enums.DataStatus;
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

    public WorkflowServiceImpl(WorkflowRepository workflowRepository,
                               BusinessEntityRepository businessEntityRepository,
                               WorkflowMapper workflowMapper) {
        this.workflowRepository = workflowRepository;
        this.businessEntityRepository = businessEntityRepository;
        this.workflowMapper = workflowMapper;
    }

    @Override
    public WorkflowResponseDto createWorkflow(WorkflowCreateRequest request) {

        if (request.getEntityId() == null) {
            throw new RuntimeException("entityId is required");
        }

        UUID entityId = UUID.fromString(request.getEntityId());
        BusinessEntity entity = businessEntityRepository.findById(entityId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        // sanitize workflowType input
        String type = request.getWorkflowType().trim().toUpperCase();

        Workflow workflow = new Workflow();
        workflow.setEntity(entity);
        workflow.setStatus(WorkflowStatus.PENDING);
        workflow.setFlowType(workflowMapper.mapFlowType(request.getFlowType()));
        workflow.setFlowTypeRaw(request.getFlowType());


        // ------------------------------------
        // HIERARCHICAL WORKFLOW
        // ------------------------------------
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

                    List<WorkflowLevelDataItem> dataItems = new ArrayList<>();

                    if (levelReq.getDataIds() != null) {
                        for (WorkflowLevelDataItemCreateRequest itemReq : levelReq.getDataIds()) {
                            WorkflowLevelDataItem item = new WorkflowLevelDataItem();
                            item.setLevel(level);
                            item.setDataId(itemReq.getDataId());
                            item.setStatus(workflowMapper.mapDataStatus(itemReq.getStatus()));
                            item.setReason(itemReq.getReason());
                            dataItems.add(item);
                        }
                    }

                    level.setDataItems(dataItems);
                    levels.add(level);
                }
            }

            workflow.setLevels(levels);
            workflow.setDataItems(new ArrayList<>()); // no parallel items

        }

        // ------------------------------------
        // PARALLEL WORKFLOW
        // ------------------------------------
        else if (type.equals("PARALLEL")) {

            workflow.setWorkflowType(WorkflowType.PARALLEL);
            workflow.setCurrentLevel(null); // irrelevant

            workflow.setLevels(new ArrayList<>()); // no levels

            List<WorkflowDataItem> dataItems = new ArrayList<>();

            if (request.getDataIds() != null) {
                for (WorkflowDataItemCreateRequest itemReq : request.getDataIds()) {
                    WorkflowDataItem item = new WorkflowDataItem();
                    item.setWorkflow(workflow);
                    item.setDataId(itemReq.getDataId());
                    item.setStatus(workflowMapper.mapDataStatus(itemReq.getStatus()));
                    item.setReason(itemReq.getReason());
                    dataItems.add(item);
                }
            }

            workflow.setDataItems(dataItems);
        }

        else {
            throw new RuntimeException("Invalid workflowType: must be HIERARCHICAL or PARALLEL");
        }

        Workflow saved = workflowRepository.save(workflow);
        return workflowMapper.toDto(saved);
    }

    @Override
    public WorkflowResponseDto getWorkflowById(UUID id) {
        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        return workflowMapper.toDto(workflow);
    }
}
