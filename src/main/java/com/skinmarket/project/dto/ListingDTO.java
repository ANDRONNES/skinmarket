package com.skinmarket.project.dto;

import com.skinmarket.project.model.entity.enums.ExteriorType;

import java.math.BigDecimal;

public record ListingDTO(
        Long listingId,
        BigDecimal price,
        String itemName,
        ExteriorType skinExterior,
        Float skinFloat,
        Integer pattern,
        String imageUrl
) { }
