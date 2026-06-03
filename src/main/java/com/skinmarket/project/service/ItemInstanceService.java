package com.skinmarket.project.service;

import com.skinmarket.project.dto.InventoryDTO;
import com.skinmarket.project.mapper.DtoMapper;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.enums.InstanceStatus;
import com.skinmarket.project.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemInstanceService {
    private final ItemInstanceRepository itemInstanceRepository;
    private final DtoMapper<InventoryDTO,ItemInstance> inventoryMapper;

    public ItemInstanceService(ItemInstanceRepository itemInstanceRepository, DtoMapper<InventoryDTO, ItemInstance> inventoryMapper) {
        this.itemInstanceRepository = itemInstanceRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional
    public ItemInstance save(ItemInstance ii) {
        return itemInstanceRepository.save(ii);
    }

    @Transactional(readOnly = true)
    public List<ItemInstance> getAll() {
        return itemInstanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<InventoryDTO> getUserInventory(Long userId){
        List<ItemInstance> inventory = itemInstanceRepository.findAll().stream()
                .filter(ii -> ii.getInventory().getUser().getUserId().equals(userId))
                .filter(ii -> ii.getStatus().equals(InstanceStatus.ININVENTORY))
                .toList();
        return inventoryMapper.toDtoList(inventory);
    }
}