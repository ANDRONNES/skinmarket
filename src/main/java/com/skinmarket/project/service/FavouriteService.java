package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.Favourite;
import com.skinmarket.project.repository.FavouriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;

    public FavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Transactional
    public Favourite save(Favourite f) {
        return favouriteRepository.save(f);
    }

    @Transactional(readOnly = true)
    public List<Favourite> getAll() {
        return favouriteRepository.findAll();
    }
}