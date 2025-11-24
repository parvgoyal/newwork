package com.parv.workflow.mapper;

import com.parv.workflow.dto.EntityDto;
import com.parv.workflow.entity.BusinessEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public EntityDto toDto(BusinessEntity entity) {
        if (entity == null) return null;

        EntityDto dto = new EntityDto();
        dto.setEntityId(entity.getId().toString());
        dto.setEntityType(entity.getEntityType());
        dto.setEntityTypeName(entity.getEntityTypeName());
        return dto;
    }
}
