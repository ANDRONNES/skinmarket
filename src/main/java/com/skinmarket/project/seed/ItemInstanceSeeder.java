package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.model.entity.ItemDefinition;
import com.skinmarket.project.model.entity.SkinInstance;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ItemInstanceSeeder implements CommandLineRunner {
    private final UserService userService;
    private final ItemDefinitionService itemDefinitionService;
    private final ItemInstanceService itemInstanceService;

    public ItemInstanceSeeder(UserService userService,
                              ItemDefinitionService itemDefinitionService,
                              ItemInstanceService itemInstanceService) {
        this.userService = userService;
        this.itemDefinitionService = itemDefinitionService;
        this.itemInstanceService = itemInstanceService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user1"))
                .findFirst().orElseThrow();
        User user2 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user2"))
                .findFirst().orElseThrow();

        ItemDefinition butterflyDef = itemDefinitionService.getAll().stream()
                .filter(d -> d.getName().contains("Butterfly"))
                .findFirst().orElseThrow();
        ItemDefinition akVulcanDef = itemDefinitionService.getAll().stream()
                .filter(d -> d.getName().contains("Vulcan"))
                .findFirst().orElseThrow();
        ItemDefinition biomechDef = itemDefinitionService.getAll().stream()
                .filter(d -> d.getName().contains("Biomech"))
                .findFirst().orElseThrow();


        SkinInstance user1Knife = SkinInstance.builder()
                .itemDefinition(butterflyDef)
                .inventory(user1.getInventory())
                .skinFloat(0.03f)
                .pattern(420)
                .build();

        SkinInstance user1Ak = SkinInstance.builder()
                .itemDefinition(akVulcanDef)
                .inventory(user1.getInventory())
                .skinFloat(0.4f)
                .pattern(422)
                .build();

        CharmInstance user1Charm = CharmInstance.builder()
                .itemDefinition(biomechDef)
                .inventory(user1.getInventory())
                .pattern(44444)
                .build();


        SkinInstance user2Ak = SkinInstance.builder()
                .itemDefinition(akVulcanDef)
                .inventory(user2.getInventory())
                .skinFloat(0.4f)
                .pattern(422)
                .build();

        CharmInstance user2Charm1 = CharmInstance.builder()
                .itemDefinition(biomechDef)
                .inventory(user2.getInventory())
                .pattern(66666)
                .build();

        CharmInstance user2Charm2 = CharmInstance.builder()
                .itemDefinition(biomechDef)
                .inventory(user2.getInventory())
                .pattern(11111)
                .build();

        CharmInstance user2Charm3 = CharmInstance.builder()
                .itemDefinition(biomechDef)
                .inventory(user2.getInventory())
                .pattern(99999)
                .build();


        itemInstanceService.save(user1Knife);
        itemInstanceService.save(user1Ak);
        itemInstanceService.save(user1Charm);
        itemInstanceService.save(user2Ak);
        itemInstanceService.save(user2Charm1);
        itemInstanceService.save(user2Charm2);
        itemInstanceService.save(user2Charm3);
    }
}