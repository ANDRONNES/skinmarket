package com.skinmarket.project.service;

import com.skinmarket.project.dto.ListingDTO;
import com.skinmarket.project.mapper.DtoMapper;
import com.skinmarket.project.mapper.ListingMapper;
import com.skinmarket.project.model.entity.Listing;
import com.skinmarket.project.repository.ListingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final DtoMapper<ListingDTO, Listing> listingMapper;

    public ListingService(ListingRepository listingRepository, DtoMapper<ListingDTO, Listing> listingMapper) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
    }

    @Transactional
    public Listing save(Listing l) {
        return listingRepository.save(l);
    }

    @Transactional(readOnly = true)
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ListingDTO> getListings(){
        return listingMapper.toDtoList(listingRepository.findAll());
    }
}