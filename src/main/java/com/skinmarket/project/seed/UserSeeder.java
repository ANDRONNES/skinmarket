package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.Inventory;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Order(2)
public class UserSeeder implements CommandLineRunner {
    private final UserService userService;

    public UserSeeder(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .username("user1")
                .email("user1@example.com")
                .balance(new BigDecimal("5000.00"))
                .tradeLink("https://steamcommunity.com/tradeoffer/new/?partner=123456")
                .birthDate(LocalDate.of(2001, 5, 12))
                .build();

        Inventory user1Inv = Inventory.builder()
                .user(user1)
                .build();

        user1.setInventory(user1Inv);

        userService.save(user1);

        User user2 = User.builder()
                .username("user2")
                .email("user2@example.com")
                .balance(new BigDecimal("150.00"))
                .build();

        Inventory user2Inv = Inventory.builder()
                .user(user2)
                .build();

        user2.setInventory(user2Inv);

        userService.save(user2);
    }
}