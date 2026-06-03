package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemInstanceService {
    private final ItemInstanceRepository itemInstanceRepository;

    public ItemInstanceService(ItemInstanceRepository itemInstanceRepository) {
        this.itemInstanceRepository = itemInstanceRepository;
    }

    @Transactional
    public ItemInstance save(ItemInstance ii) {
        return itemInstanceRepository.save(ii);
    }

    @Transactional(readOnly = true)
    public List<ItemInstance> getAll() {
        return itemInstanceRepository.findAll();
    }
}