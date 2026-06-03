package com.skinmarket.project.controller;

import com.skinmarket.project.dto.InventoryDTO;
import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.service.InventoryService;
import com.skinmarket.project.service.ItemInstanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/inventory")
public class InventoryController {

    private final ItemInstanceService itemInstanceService;

    public InventoryController(ItemInstanceService itemInstanceService) {
        this.itemInstanceService = itemInstanceService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<InventoryDTO>> GetInventory(@PathVariable Long userId){
        List<InventoryDTO> inventory = itemInstanceService.getUserInventory(userId);
        return ResponseEntity.ok(inventory);
    }
}
