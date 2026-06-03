package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.ItemCollection;
import com.skinmarket.project.service.ItemCollectionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ItemCollectionSeeder implements CommandLineRunner {
    private final ItemCollectionService collectionService;

    public ItemCollectionSeeder(ItemCollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Override
    public void run(String... args) throws Exception {
        ItemCollection breakout = ItemCollection.builder()
                .name("Operation Breakout Weapon Case")
                .description("The Breakout Collection was released on July 1st, 2014, alongside the \"Operation Breakout\" update.")
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGJKz2lu_XsnXwtmkJjSU91dh8bj35VTqVBP4io_fqWxdv_b8O_w5eKXBWWXHw-smtrBvTHDmwEsl4jvWn4z_I3qWZwV1X5ZwW6dU5RcRF1o0")
                .build();
        collectionService.save(breakout);

        ItemCollection phoenix = ItemCollection.builder()
                .name("Operation Phoenix Weapon Case")
                .description("Browse and buy all CS2 skins from the Phoenix Collection. The Phoenix Collection was released on February 20th, 2014, alongside the \"The Rise of Operation Phoenix\" update.")
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGJKz2lu_XsnXwtmkJjSU91dh8bj35VTqVBP4io_fr2wPtqP5PKVvJPSQDWSSl7sn6eMxHC3hwhl3sDuDztivJHrEagJzWZd3W6dU5fXcT7oM")
                .build();
        collectionService.save(phoenix);
    }
}
