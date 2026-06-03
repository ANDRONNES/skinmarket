package com.skinmarket.project.seed;
import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.Listing;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.model.entity.enums.InstanceStatus;
import com.skinmarket.project.service.ItemInstanceService;
import com.skinmarket.project.service.ListingService;
import com.skinmarket.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Order(6)
@Component
public class ListingSeeder implements CommandLineRunner {
    private final UserService userService;
    private final ItemInstanceService itemInstanceService;
    private final ListingService listingService;

    public ListingSeeder(UserService userService, ItemInstanceService itemInstanceService, ListingService listingService) {
        this.userService = userService;
        this.itemInstanceService = itemInstanceService;
        this.listingService = listingService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user2 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user2"))
                .findFirst().orElseThrow();
        List<ItemInstance> itemInstances = itemInstanceService.getAll().stream()
                .filter(ii -> ii instanceof CharmInstance)
                .filter(ii -> ii.getInventory().getUser().getUserId().equals(user2.getUserId()))
                .toList();

        for (ItemInstance ii : itemInstances){
            Listing listing = Listing.builder()
                    .price(new BigDecimal(5))
                    .seller(user2)
                    .itemInstance(ii)
                    .build();
            ii.setActiveListing(listing);
            ii.setStatus(InstanceStatus.ONSALE);
            listingService.save(listing);
            itemInstanceService.save(ii);
        }
    }
}
