package com.skinmarket.project.controller;

import com.skinmarket.project.dto.DepositItemRequestDTO;
import com.skinmarket.project.dto.ItemDefinitionDTO;
import com.skinmarket.project.service.DepositService;
import com.skinmarket.project.service.ItemDefinitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deposit")
public class DepositController {

    private final ItemDefinitionService itemDefinitionService;
    private final DepositService depositService;

    public DepositController(ItemDefinitionService itemDefinitionService, DepositService depositService) {
        this.itemDefinitionService = itemDefinitionService;
        this.depositService = depositService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDefinitionDTO>> getItemsDefs(){
        List<ItemDefinitionDTO> items = itemDefinitionService.getItemDefs();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/items/{userId}")
    public ResponseEntity<String> depositItem(@PathVariable Long userId, @RequestBody DepositItemRequestDTO itemDefDto){
        depositService.depositItem(userId, itemDefDto.itemDefinitionId());
        return ResponseEntity.ok("Item has been deposited!");
    }
}
