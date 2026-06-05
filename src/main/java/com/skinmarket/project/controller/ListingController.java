package com.skinmarket.project.controller;

import com.skinmarket.project.dto.BuyRequestDTO;
import com.skinmarket.project.dto.ListingDTO;
import com.skinmarket.project.service.ListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/listings")
    public ResponseEntity<List<ListingDTO>> getListings(){
        var activeListings = listingService.getListings();
        return ResponseEntity.ok(activeListings);
    }

    @PostMapping("/listings/{listingId}/buy")
    public ResponseEntity<String> buyListedItem(@PathVariable Long listingId, @RequestBody BuyRequestDTO userId){
        listingService.buyListedItem(listingId, userId.buyerId());
        return ResponseEntity.ok("Item has been bought, check your inventory");
    }
}
