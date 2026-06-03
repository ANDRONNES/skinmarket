package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.Listing;
import com.skinmarket.project.repository.ListingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Transactional
    public Listing save(Listing l) {
        return listingRepository.save(l);
    }

    @Transactional(readOnly = true)
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }
}