package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.ItemCollection;
import com.skinmarket.project.model.entity.ItemDefinition;
import com.skinmarket.project.model.entity.enums.ItemType;
import com.skinmarket.project.model.entity.Quality;
import com.skinmarket.project.model.entity.enums.WeaponType;
import com.skinmarket.project.service.ItemCollectionService;
import com.skinmarket.project.service.ItemDefinitionService;
import com.skinmarket.project.service.QualityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ItemDefinitionSeeder implements CommandLineRunner {
    private final ItemDefinitionService itemDefinitionService;
    private final QualityService qualityService;
    private final ItemCollectionService collectionService;

    public ItemDefinitionSeeder(ItemDefinitionService itemDefinitionService,
                                QualityService qualityService,
                                ItemCollectionService collectionService) {
        this.itemDefinitionService = itemDefinitionService;
        this.qualityService = qualityService;
        this.collectionService = collectionService;
    }

    @Override
    public void run(String... args) throws Exception {
        Quality covert = qualityService.getAll().stream()
                .filter(q -> q.getQualityName().name().equals("COVERT"))
                .findFirst().orElseThrow();
        Quality milspec = qualityService.getAll().stream()
                .filter(q -> q.getQualityName().name().equals("MILSPEC"))
                .findFirst().orElseThrow();

        ItemCollection breakout = collectionService.getAll().stream()
                .filter(c -> c.getName().contains("Breakout"))
                .findFirst().orElseThrow();
        ItemCollection phoenix = collectionService.getAll().stream()
                .filter(c -> c.getName().contains("Phoenix"))
                .findFirst().orElseThrow();

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
                .build();
        itemDefinitionService.save(biomech);
    }
}