package com.skinmarket.project.mapper;

import com.skinmarket.project.dto.InventoryDTO;
import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.SkinInstance;
import org.springframework.stereotype.Component;


@Component
public class InventoryMapper implements DtoMapper<InventoryDTO, ItemInstance> {
    @Override
    public InventoryDTO toDto(ItemInstance itemInstance) {
        if (itemInstance == null) {
            return null;
        }

        if (itemInstance instanceof SkinInstance skin) {
            return new InventoryDTO(
                    itemInstance.getItemInstanceId(),
                    itemInstance.getItemDefinition().getName(),
                    skin.getExterior(),
                    skin.getSkinFloat(),
                    skin.getPattern()
            );
        }

        if (itemInstance instanceof CharmInstance charm) {
            return new InventoryDTO(
                    itemInstance.getItemInstanceId(),
                    itemInstance.getItemDefinition().getName(),
                    null,
                    null,
                    charm.getPattern()
            );
        }

        throw new IllegalStateException("Unknown item type: " + itemInstance.getClass());
    }
}
