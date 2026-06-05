package com.skinmarket.project.service;

import com.skinmarket.project.dto.InventoryDTO;
import com.skinmarket.project.mapper.DtoMapper;
import com.skinmarket.project.model.entity.Inventory;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.enums.InstanceStatus;
import com.skinmarket.project.repository.InventoryRepository;
import com.skinmarket.project.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ItemInstanceRepository itemInstanceRepository;
    private final DtoMapper inventoryMapper;

    public InventoryService(InventoryRepository inventoryRepository, ItemInstanceRepository itemInstanceRepository, DtoMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.itemInstanceRepository = itemInstanceRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional
    public Inventory save(Inventory i) {
        return inventoryRepository.save(i);
    }

    @Transactional(readOnly = true)
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<InventoryDTO> getUserInventory(Long userId) {
        List<ItemInstance> inventory = itemInstanceRepository.findAll().stream()
                .filter(ii -> ii.getInventory().getUser().getUserId().equals(userId))
                .filter(ii -> ii.getStatus().equals(InstanceStatus.ININVENTORY))
                .toList();

        return inventoryMapper.toDtoList(inventory);
    }
}