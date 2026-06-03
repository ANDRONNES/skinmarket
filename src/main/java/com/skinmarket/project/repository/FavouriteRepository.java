package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}
