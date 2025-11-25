package com.parv.workflow.repository;

import com.parv.workflow.entity.Workflow;
import com.parv.workflow.enums.FlowType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkflowRepository extends JpaRepository<Workflow, UUID> {

    List<Workflow> findByFlowType(FlowType flowType);

}
