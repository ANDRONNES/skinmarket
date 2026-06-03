package com.skinmarket.project.seed;
import com.skinmarket.project.model.entity.BuyOrder;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.Transaction;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.model.entity.enums.TransactionStatus;
import com.skinmarket.project.model.entity.enums.TransactionType;
import com.skinmarket.project.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Order(7)
@Component
public class TransactionSeeder implements CommandLineRunner {

    private final UserService userService;
    private final BuyOrderService buyOrderService;
    private final ListingService listingService;
    private final TransactionService transactionService;
    private final ItemInstanceService itemInstanceService;

    public TransactionSeeder(UserService userService, BuyOrderService buyOrderService, ListingService listingService, TransactionService transactionService, ItemInstanceService itemInstanceService) {
        this.userService = userService;
        this.buyOrderService = buyOrderService;
        this.listingService = listingService;
        this.transactionService = transactionService;
        this.itemInstanceService = itemInstanceService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user1 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user1"))
                .findFirst().orElseThrow();

        User user2 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user2"))
                .findFirst().orElseThrow();

        BuyOrder buyOrder = buyOrderService.getAll().stream()
                .filter(bo -> bo.getItemDefinition().getName().contains("Butterfly"))
                .findFirst().orElseThrow();

        ItemInstance itemInstance = itemInstanceService.getAll().stream()
                .filter(ii -> ii.getItemDefinition().getName().contains("Butterfly"))
                .findFirst().orElseThrow();

        Transaction transaction = Transaction.builder()
                .buyer(user1)
                .seller(user2)
                .buyOrder(buyOrder)
                .createdAt(buyOrder.getClosedAt())
                .price(buyOrder.getTargetPrice())
                .itemInstance(itemInstance)
                .transactionType(TransactionType.BUYORDERMATCH)
                .transactionStatus(TransactionStatus.SUCCESS)
                .build();

        transactionService.save(transaction);

        buyOrder.setTransaction(transaction);
        buyOrderService.save(buyOrder);
    }
}
