package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.ItemCollection;
import com.skinmarket.project.repository.ItemCollectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemCollectionService {
    private final ItemCollectionRepository itemCollectionRepository;

    public ItemCollectionService(ItemCollectionRepository itemCollectionRepository) {
        this.itemCollectionRepository = itemCollectionRepository;
    }

    @Transactional
    public ItemCollection save(ItemCollection ic) {
        return itemCollectionRepository.save(ic);
    }

    @Transactional(readOnly = true)
    public List<ItemCollection> getAll() {
        return itemCollectionRepository.findAll();
    }
}