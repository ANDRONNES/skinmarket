package com.skinmarket.project.service;

import com.skinmarket.project.exception.NotFoundException;
import com.skinmarket.project.model.entity.*;
import com.skinmarket.project.model.entity.enums.InstanceStatus;
import com.skinmarket.project.model.entity.enums.ItemType;
import com.skinmarket.project.repository.ItemDefinitionRepository;
import com.skinmarket.project.repository.ItemInstanceRepository;
import com.skinmarket.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class DepositService {

    private final ItemDefinitionRepository itemDefinitionRepository;
    private final ItemInstanceRepository itemInstanceRepository;
    private final UserRepository userRepository;
    private final Random random = new Random();

    private static final float MIN_SKIN_FLOAT = 0.0001f;
    private static final float MAX_SKIN_FLOAT = 1.0000f;
    private static final int MAX_SKIN_PATTERN = 1000;
    private static final int MAX_CHARM_PATTERN = 100000;

    public DepositService(ItemDefinitionRepository itemDefinitionRepository, ItemInstanceRepository itemInstanceRepository, UserRepository userRepository) {
        this.itemDefinitionRepository = itemDefinitionRepository;
        this.itemInstanceRepository = itemInstanceRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void depositItem(Long userId, Long itemDefinitionId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        ItemDefinition definition = itemDefinitionRepository.findById(itemDefinitionId)
                .orElseThrow(() -> new NotFoundException("Item not found"));

        ItemType type = definition.getItemType();
        var inventory = user.getInventory();
        var status = InstanceStatus.ININVENTORY;

        switch (type) {
            case SKIN -> {
                float randFloat = MIN_SKIN_FLOAT + (random.nextFloat() * (MAX_SKIN_FLOAT - MIN_SKIN_FLOAT));
                int randPattern = random.nextInt(MAX_SKIN_PATTERN) + 1;

                SkinInstance skin = SkinInstance.builder()
                        .itemDefinition(definition)
                        .inventory(inventory)
                        .status(status)
                        .skinFloat(randFloat)
                        .pattern(randPattern)
                        .build();
                itemInstanceRepository.save(skin);
            }
            case CHARM -> {
                int randPattern = random.nextInt(MAX_CHARM_PATTERN) + 1;

                CharmInstance charm = CharmInstance.builder()
                        .itemDefinition(definition)
                        .inventory(inventory)
                        .status(status)
                        .pattern(randPattern)
                        .build();
                itemInstanceRepository.save(charm);
            }
            case AGENT, STICKER -> {
                DefaultInstance defaultItem = DefaultInstance.builder()
                    .itemDefinition(definition)
                    .inventory(inventory)
                    .status(status)
                    .build();
                itemInstanceRepository.save(defaultItem);
            }
        }
    }
}
