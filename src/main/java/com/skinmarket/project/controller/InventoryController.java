package com.skinmarket.project.controller;

import com.skinmarket.project.dto.InventoryDTO;
import com.skinmarket.project.dto.PriceRequestDTO;
import com.skinmarket.project.service.InventoryService;
import com.skinmarket.project.service.ListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final ListingService listingService;

    public InventoryController(InventoryService inventoryService, ListingService listingService) {
        this.inventoryService = inventoryService;
        this.listingService = listingService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<InventoryDTO>> getInventory(@PathVariable Long userId) {
        List<InventoryDTO> inventory = inventoryService.getUserInventory(userId);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/{userId}/items/{itemInstanceId}/list")
    public ResponseEntity<String> listOnMarket(
            @PathVariable Long itemInstanceId,
            @PathVariable("userId") Long sellerId,
            @RequestBody PriceRequestDTO priceDTO) {

        listingService.listOnMarket(itemInstanceId, sellerId, priceDTO.price());
        return ResponseEntity.ok("Item successfully listed on market for " + priceDTO.price());
    }

    @PostMapping("/{userId}/items/{itemInstanceId}/instant")
    public ResponseEntity<String> instantSell(
            @PathVariable Long itemInstanceId,
            @PathVariable("userId") Long sellerId) {

        listingService.instantSell(itemInstanceId, sellerId);
        return ResponseEntity.ok("Item has been sold!");
    }
}
