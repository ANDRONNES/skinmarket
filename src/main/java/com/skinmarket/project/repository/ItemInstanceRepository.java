package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.ItemInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInstanceRepository extends JpaRepository<ItemInstance, Long> {
}
