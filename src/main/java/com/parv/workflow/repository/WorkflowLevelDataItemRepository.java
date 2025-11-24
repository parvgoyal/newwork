package com.parv.workflow.repository;

import com.parv.workflow.entity.WorkflowLevelDataItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkflowLevelDataItemRepository extends JpaRepository<WorkflowLevelDataItem, UUID> {
}
