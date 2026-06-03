package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.ItemCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCollectionRepository extends JpaRepository<ItemCollection, Long> {
}
