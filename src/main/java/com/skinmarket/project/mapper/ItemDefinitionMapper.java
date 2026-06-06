package com.skinmarket.project.mapper;

import com.skinmarket.project.dto.ItemDefinitionDTO;
import com.skinmarket.project.model.entity.ItemDefinition;
import org.springframework.stereotype.Component;

@Component
public class ItemDefinitionMapper implements DtoMapper<ItemDefinitionDTO, ItemDefinition> {
    @Override
    public ItemDefinitionDTO toDto(ItemDefinition entity) {
        return new ItemDefinitionDTO(
                entity.getItemDefinitionId(),
                entity.getName(),
                entity.getImageUrl()
        );
    }
}
