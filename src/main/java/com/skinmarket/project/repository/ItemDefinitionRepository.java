package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.ItemDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDefinitionRepository extends JpaRepository<ItemDefinition, Long> {
}
