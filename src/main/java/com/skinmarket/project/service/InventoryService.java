package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.Inventory;
import com.skinmarket.project.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public Inventory save(Inventory i) {
        return inventoryRepository.save(i);
    }

    @Transactional(readOnly = true)
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }
}