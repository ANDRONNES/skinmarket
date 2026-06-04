package com.skinmarket.project.mapper;

import com.skinmarket.project.dto.InventoryDTO;
import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.SkinInstance;
import com.skinmarket.project.model.entity.enums.BuyOrderStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;


@Component
public class InventoryMapper implements DtoMapper<InventoryDTO, ItemInstance> {
    @Override
    public InventoryDTO toDto(ItemInstance itemInstance) {
        if (itemInstance == null) {
            return null;
        }
        BigDecimal instantSellPrice = itemInstance.getItemDefinition().getBuyOrderList().stream()
                .filter(bo -> bo.getBuyOrderStatus() == BuyOrderStatus.ACTIVE)
                .map(bo -> bo.getTargetPrice())
                .max(Comparator.naturalOrder())
                .orElse(java.math.BigDecimal.ZERO);

        if (itemInstance instanceof SkinInstance skin) {
            return new InventoryDTO(
                    itemInstance.getItemInstanceId(),
                    itemInstance.getItemDefinition().getName(),
                    skin.getExterior(),
                    skin.getSkinFloat(),
                    skin.getPattern(),
                    instantSellPrice
            );
        }

        if (itemInstance instanceof CharmInstance charm) {
            return new InventoryDTO(
                    itemInstance.getItemInstanceId(),
                    itemInstance.getItemDefinition().getName(),
                    null,
                    null,
                    charm.getPattern(),
                    instantSellPrice
            );
        }

        throw new IllegalStateException("Unknown item type: " + itemInstance.getClass());
    }
}
