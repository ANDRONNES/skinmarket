package com.skinmarket.project.seed;
import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.model.entity.Favourite;
import com.skinmarket.project.model.entity.Listing;
import com.skinmarket.project.model.entity.User;
import com.skinmarket.project.service.FavouriteService;
import com.skinmarket.project.service.ListingService;
import com.skinmarket.project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Order(9)
@Component
public class FavouriteSeeder implements CommandLineRunner {

    private final UserService userService;
    private final ListingService listingService;
    private final FavouriteService favouriteService;

    public FavouriteSeeder(UserService userService, ListingService listingService, FavouriteService favouriteService) {
        this.userService = userService;
        this.listingService = listingService;
        this.favouriteService = favouriteService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user1 = userService.getAll().stream()
                .filter(u -> u.getUsername().equals("user1"))
                .findFirst().orElseThrow();
        Listing listing1 = listingService.getAll().stream()
                .filter(l -> l.getListingId() == 3l)
                .findFirst().orElseThrow();

        Favourite favourite = Favourite.builder()
                .priceAtMoment(listing1.getPrice())
                .user(user1)
                .listing(listing1)
                .build();

        favouriteService.save(favourite);
    }
}
