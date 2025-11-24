package com.parv.workflow.controller;

import com.parv.workflow.entity.BusinessEntity;
import com.parv.workflow.repository.BusinessEntityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/entities")
public class BusinessEntityController {

    private final BusinessEntityRepository repository;

    public BusinessEntityController(BusinessEntityRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public BusinessEntity create(@RequestBody BusinessEntity entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        return repository.save(entity);
    }

    @GetMapping("/{id}")
    public BusinessEntity get(@PathVariable UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
    }
}
