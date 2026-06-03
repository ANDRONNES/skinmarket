package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    @Query("SELECT l FROM Listing l LEFT JOIN FETCH l.itemInstance ii LEFT JOIN FETCH ii.itemDefinition")
    List<Listing> findAllWithItems();

    @Query("SELECT MIN(l.price) FROM Listing l WHERE l.itemInstance.itemDefinition.itemDefinitionId = :defId")
    BigDecimal findMinPriceByItemDefId(@Param("defId") Long defId);
}