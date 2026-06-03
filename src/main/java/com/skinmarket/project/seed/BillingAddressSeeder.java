package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.BillingAddress;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.service.BillingAddressService;
import com.skinmarket.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BillingAddressSeeder implements CommandLineRunner {

    private final UserService userService;
    private final BillingAddressService billingAddressService;

    public BillingAddressSeeder(UserService userService, BillingAddressService billingAddressService){
        this.userService = userService;
        this.billingAddressService = billingAddressService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user1"))
                .findFirst().orElseThrow();

        BillingAddress ba1 = BillingAddress.builder()
                .country("Poland")
                .city("Warsaw")
                .postalCode("00-000")
                .street("Chmielna")
                .buildingNumber(1)
                .apartmentNumber(1)
                .belongsTo(user1)
                .build();
        billingAddressService.save(ba1);

        User user2 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user2"))
                .findFirst().orElseThrow();
        BillingAddress ba2 = BillingAddress.builder()
                .country("Poland")
                .city("Warsaw")
                .postalCode("00-000")
                .street("Chmielna")
                .buildingNumber(2)
                .belongsTo(user2)
                .build();
        billingAddressService.save(ba2);
    }
}
