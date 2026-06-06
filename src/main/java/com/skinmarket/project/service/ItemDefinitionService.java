package com.skinmarket.project.service;

import com.skinmarket.project.dto.ItemDefinitionDTO;
import com.skinmarket.project.mapper.DtoMapper;
import com.skinmarket.project.model.entity.ItemDefinition;
import com.skinmarket.project.repository.ItemDefinitionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemDefinitionService {
    private final ItemDefinitionRepository itemDefinitionRepository;
    private final DtoMapper itemDefinitionMapper;

    public ItemDefinitionService(ItemDefinitionRepository itemDefinitionRepository, DtoMapper itemDefinitionMapper) {
        this.itemDefinitionRepository = itemDefinitionRepository;
        this.itemDefinitionMapper = itemDefinitionMapper;
    }

    @Transactional
    public ItemDefinition save(ItemDefinition id) {
        return itemDefinitionRepository.save(id);
    }

    @Transactional(readOnly = true)
    public List<ItemDefinition> getAll() {
        return itemDefinitionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ItemDefinitionDTO> getItemDefs(){
        List<ItemDefinition> items = itemDefinitionRepository.findAll();
        return itemDefinitionMapper.toDtoList(items);
    }
}