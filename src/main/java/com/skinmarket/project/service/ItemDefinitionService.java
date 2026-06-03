package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.ItemDefinition;
import com.skinmarket.project.repository.ItemDefinitionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemDefinitionService {
    private final ItemDefinitionRepository itemDefinitionRepository;

    public ItemDefinitionService(ItemDefinitionRepository itemDefinitionRepository) {
        this.itemDefinitionRepository = itemDefinitionRepository;
    }

    @Transactional
    public ItemDefinition save(ItemDefinition id) {
        return itemDefinitionRepository.save(id);
    }

    @Transactional(readOnly = true)
    public List<ItemDefinition> getAll() {
        return itemDefinitionRepository.findAll();
    }
}