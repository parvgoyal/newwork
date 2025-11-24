package com.parv.workflow.repository;

import com.parv.workflow.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessEntityRepository extends JpaRepository<BusinessEntity, UUID> {
}
