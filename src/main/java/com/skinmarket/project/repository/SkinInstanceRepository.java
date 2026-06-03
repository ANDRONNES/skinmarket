package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.SkinInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkinInstanceRepository extends JpaRepository<SkinInstance, Long> {
}
