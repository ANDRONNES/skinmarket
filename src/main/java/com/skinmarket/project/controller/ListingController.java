package com.skinmarket.project.controller;

import com.skinmarket.project.dto.ListingDTO;
import com.skinmarket.project.service.ListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ListingDTO>> GetListings(){
        var activeListings = listingService.getListings();
        return ResponseEntity.ok(activeListings);
    }
}
