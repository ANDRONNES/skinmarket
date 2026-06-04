package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.*;
import com.skinmarket.project.model.entity.enums.*;
import com.skinmarket.project.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {

    private final QualityService qualityService;
    private final ItemCollectionService itemCollectionService;
    private final UserService userService;
    private final ItemDefinitionService itemDefinitionService;
    private final ItemInstanceService itemInstanceService;
    private final BuyOrderService buyOrderService;
    private final TransactionService transactionService;
    private final ListingService listingService;
    private final BillingAddressService billingAddressService;
    private final FavouriteService favouriteService;

    public DbInitializer(QualityService qualityService, ItemCollectionService itemCollectionService, UserService userService, ItemDefinitionService itemDefinitionService, ItemInstanceService itemInstanceService, BuyOrderService buyOrderService, TransactionService transactionService, ListingService listingService, BillingAddressService billingAddressService, FavouriteService favouriteService) {
        this.qualityService = qualityService;
        this.itemCollectionService = itemCollectionService;
        this.userService = userService;
        this.itemDefinitionService = itemDefinitionService;
        this.itemInstanceService = itemInstanceService;
        this.buyOrderService = buyOrderService;
        this.transactionService = transactionService;
        this.listingService = listingService;
        this.billingAddressService = billingAddressService;
        this.favouriteService = favouriteService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Quality
        Quality milspec = Quality.builder()
                .qualityName(QualityName.MILSPEC)
                .qualityColor(QualityColor.BLUE)
                .build();

        qualityService.save(milspec);

        Quality restricted = Quality.builder()
                .qualityName(QualityName.RESTRICTED)
                .qualityColor(QualityColor.PURPLE)
                .build();

        qualityService.save(restricted);

        Quality classified = Quality.builder()
                .qualityName(QualityName.CLASSIFIED)
                .qualityColor(QualityColor.PINK)
                .build();

        qualityService.save(classified);

        Quality covert = Quality.builder()
                .qualityName(QualityName.COVERT)
                .qualityColor(QualityColor.RED)
                .build();

        qualityService.save(covert);


        //ItemCollection
        ItemCollection breakout = ItemCollection.builder()
                .name("Operation Breakout Weapon Case")
                .description("The Breakout Collection was released on July 1st, 2014, alongside the \"Operation Breakout\" update.")
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGJKz2lu_XsnXwtmkJjSU91dh8bj35VTqVBP4io_fqWxdv_b8O_w5eKXBWWXHw-smtrBvTHDmwEsl4jvWn4z_I3qWZwV1X5ZwW6dU5RcRF1o0")
                .build();
        itemCollectionService.save(breakout);

        ItemCollection phoenix = ItemCollection.builder()
                .name("Operation Phoenix Weapon Case")
                .description("Browse and buy all CS2 skins from the Phoenix Collection. The Phoenix Collection was released on February 20th, 2014, alongside the \"The Rise of Operation Phoenix\" update.")
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGJKz2lu_XsnXwtmkJjSU91dh8bj35VTqVBP4io_fr2wPtqP5PKVvJPSQDWSSl7sn6eMxHC3hwhl3sDuDztivJHrEagJzWZd3W6dU5fXcT7oM")
                .build();
        itemCollectionService.save(phoenix);

        //User
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

        //ItemDefinition
        ItemDefinition butterflyFade = ItemDefinition.builder()
                .name("Butterfly Knife | Fade")
                .itemType(ItemType.SKIN)
                .weaponType(WeaponType.KNIFE)
                .quality(covert)
                .itemCollection(breakout)
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGIGz3UqlXOLrxM-vMGmW8VNxu5Dx60noTyL6kJ_m-B1Z-ua6bbZrLOmsD2avx-9ytd5lRi67gVNwsDvSwtqqc3iXZg4kCZYjReYLtRbum9XgYuvm5wbWjtgUzCn3iSsf8G81tFEeH9rw")
                .previewInGameLink("https://steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20S7656119808")
                .build();
        itemDefinitionService.save(butterflyFade);

        ItemDefinition akVulcan = ItemDefinition.builder()
                .name("AK-47 | Vulcan")
                .itemType(ItemType.SKIN)
                .weaponType(WeaponType.GUN)
                .quality(covert)
                .itemCollection(breakout)
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGIGz3UqlXOLrxM-vMGmW8VNxu5Dx60noTyLwlcK3wiFO0POlPPNSMuWRDGKC_uJ_t-l9AXCxxEh14zjTztivci2ePQZ2W8NzTecD4BKwloLiYeqxtAOIj9gUyyngznQeF7I6QE8")
                .build();
        itemDefinitionService.save(akVulcan);

        ItemDefinition biomech = ItemDefinition.builder()
                .name("Charm | Biomech")
                .itemType(ItemType.CHARM)
                .quality(milspec)
                .itemCollection(phoenix)
                .imageUrl("https://community.steamstatic.com/economy/image/i0CoZ81Ui0m-9KwlBY1L_18myuGuq1wfhWSaZgMttyVfPaERSR0Wqmu7LAocGI6zwki4Uf_a0IWlJ3mY6ls6_4TL7lvYQxT-k5Pj9WxauvSrbPE-JanDV2TIkO1y6bBoGiu1kEojtjvVzdz_ci2fbQEhCsZ2W6dU5Z-pmlUG")
                .build();
        itemDefinitionService.save(biomech);

        //ItemInstance
        SkinInstance user1Knife = SkinInstance.builder()
                .itemDefinition(butterflyFade)
                .inventory(user1.getInventory())
                .skinFloat(0.03f)
                .pattern(420)
                .build();

        SkinInstance user1Ak = SkinInstance.builder()
                .itemDefinition(akVulcan)
                .inventory(user1.getInventory())
                .skinFloat(0.4f)
                .pattern(422)
                .build();

        CharmInstance user1Charm1 = CharmInstance.builder()
                .itemDefinition(biomech)
                .inventory(user1.getInventory())
                .pattern(44444)
                .build();

        CharmInstance user1Charm2 = CharmInstance.builder()
                .itemDefinition(biomech)
                .inventory(user1.getInventory())
                .pattern(44444)
                .build();

        CharmInstance user1Charm3 = CharmInstance.builder()
                .itemDefinition(biomech)
                .inventory(user1.getInventory())
                .pattern(44444)
                .build();


        SkinInstance user2Ak = SkinInstance.builder()
                .itemDefinition(akVulcan)
                .inventory(user2.getInventory())
                .skinFloat(0.06f)
                .pattern(422)
                .build();

        CharmInstance user2Charm1 = CharmInstance.builder()
                .itemDefinition(biomech)
                .inventory(user2.getInventory())
                .pattern(66666)
                .build();

        CharmInstance user2Charm2 = CharmInstance.builder()
                .itemDefinition(biomech)
                .inventory(user2.getInventory())
                .pattern(11111)
                .build();

        CharmInstance user2Charm3 = CharmInstance.builder()
                .itemDefinition(biomech)
                .inventory(user2.getInventory())
                .pattern(99999)
                .build();


        itemInstanceService.save(user1Knife);
        itemInstanceService.save(user1Ak);
        itemInstanceService.save(user1Charm1);
        itemInstanceService.save(user1Charm2);
        itemInstanceService.save(user1Charm3);
        itemInstanceService.save(user2Ak);
        itemInstanceService.save(user2Charm1);
        itemInstanceService.save(user2Charm2);
        itemInstanceService.save(user2Charm3);

        //BuyOrder
        BuyOrder buyOrder1 = BuyOrder.builder()
                .targetPrice(new BigDecimal(100))
                .itemDefinition(akVulcan)
                .buyer(user2)
                .build();

        BuyOrder buyOrder2 = BuyOrder.builder()
                .targetPrice(new BigDecimal(0.33))
                .itemDefinition(biomech)
                .buyer(user2)
                .build();

        BuyOrder buyOrder3 = BuyOrder.builder()
                .targetPrice(new BigDecimal(10))
                .itemDefinition(butterflyFade)
                .buyOrderStatus(BuyOrderStatus.CLOSED)
                .buyer(user1)
                .createdAt(LocalDateTime.now().minusMinutes(10))
                .closedAt(LocalDateTime.now())
                .build();

        buyOrderService.save(buyOrder1);
        buyOrderService.save(buyOrder2);
        buyOrderService.save(buyOrder3);

        //Transaction
        Transaction transaction = Transaction.builder()
                .buyer(user1)
                .seller(user2)
                .buyOrder(buyOrder3)
                .createdAt(buyOrder3.getClosedAt())
                .price(buyOrder3.getTargetPrice())
                .itemInstance(user1Knife)
                .transactionType(TransactionType.BUYORDERMATCH)
                .transactionStatus(TransactionStatus.SUCCESS)
                .build();

        transactionService.save(transaction);

        buyOrder3.setTransaction(transaction);
        buyOrderService.save(buyOrder3);

        //Listing
        List<ItemInstance> itemInstances = List.of(user2Charm1, user2Charm2, user2Charm3, user2Ak);

        for (ItemInstance ii : itemInstances) {
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

        //BillingAddress
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

        BillingAddress ba2 = BillingAddress.builder()
                .country("Poland")
                .city("Warsaw")
                .postalCode("00-000")
                .street("Chmielna")
                .buildingNumber(2)
                .belongsTo(user2)
                .build();
        billingAddressService.save(ba2);

        //Favourite
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
