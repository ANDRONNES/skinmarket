package com.skinmarket.project.seed;

import com.skinmarket.project.model.entity.Quality;
import com.skinmarket.project.model.entity.enums.QualityColor;
import com.skinmarket.project.model.entity.enums.QualityName;
import com.skinmarket.project.service.QualityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class QualitySeeder implements CommandLineRunner {
    private final QualityService qualityService;

    public QualitySeeder(QualityService qualityService) {
        this.qualityService = qualityService;
    }

    @Override
    public void run(String... args) throws Exception {
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
    }


}
