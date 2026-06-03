package com.skinmarket.project.seed;
import com.skinmarket.project.model.entity.BuyOrder;
import com.skinmarket.project.model.entity.ItemDefinition;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.model.entity.enums.BuyOrderStatus;
import com.skinmarket.project.service.BuyOrderService;
import com.skinmarket.project.service.ItemDefinitionService;
import com.skinmarket.project.service.TransactionService;
import com.skinmarket.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Order(5)
@Component
public class BuyOrderSeeder implements CommandLineRunner {
    private final UserService userService;
    private final ItemDefinitionService itemDefinitionService;
    private final BuyOrderService buyOrderService;

    public BuyOrderSeeder(UserService userService, ItemDefinitionService itemDefinitionService, TransactionService transactionService, BuyOrderService buyOrderService) {
        this.userService = userService;
        this.itemDefinitionService = itemDefinitionService;
        this.buyOrderService = buyOrderService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user1"))
                .findFirst().orElseThrow();

        User user2 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user2"))
                .findFirst().orElseThrow();

        ItemDefinition akVulcanDef = itemDefinitionService.getAll().stream()
                .filter(d -> d.getName().contains("Vulcan"))
                .findFirst().orElseThrow();

        ItemDefinition biomechDef = itemDefinitionService.getAll().stream()
                .filter(d -> d.getName().contains("Biomech"))
                .findFirst().orElseThrow();

        ItemDefinition butterflyDef = itemDefinitionService.getAll().stream()
                .filter(d -> d.getName().contains("Butterfly"))
                .findFirst().orElseThrow();

        BuyOrder buyOrder1 = BuyOrder.builder()
                .targetPrice(new BigDecimal(100))
                .itemDefinition(akVulcanDef)
                .buyer(user2)
                .build();

        BuyOrder buyOrder2 = BuyOrder.builder()
                .targetPrice(new BigDecimal(0.33f))
                .itemDefinition(biomechDef)
                .buyer(user2)
                .build();

        BuyOrder buyOrder3 = BuyOrder.builder()
                .targetPrice(new BigDecimal(10))
                .itemDefinition(butterflyDef)
                .buyOrderStatus(BuyOrderStatus.CLOSED)
                .buyer(user1)
                .createdAt(LocalDateTime.now().minusMinutes(10))
                .closedAt(LocalDateTime.now())
                .build();

        buyOrderService.save(buyOrder1);
        buyOrderService.save(buyOrder2);
        buyOrderService.save(buyOrder3);
    }
}
