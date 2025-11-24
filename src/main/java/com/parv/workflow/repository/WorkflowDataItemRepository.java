package com.parv.workflow.repository;

import com.parv.workflow.entity.WorkflowDataItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkflowDataItemRepository extends JpaRepository<WorkflowDataItem, UUID> {
}
