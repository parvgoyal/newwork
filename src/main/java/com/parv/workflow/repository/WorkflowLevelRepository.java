package com.parv.workflow.repository;

import com.parv.workflow.entity.WorkflowLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkflowLevelRepository extends JpaRepository<WorkflowLevel, UUID> {
}
