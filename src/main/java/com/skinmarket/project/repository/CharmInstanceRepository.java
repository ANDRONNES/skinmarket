package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.CharmInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharmInstanceRepository extends JpaRepository<CharmInstance, Long> {
}
