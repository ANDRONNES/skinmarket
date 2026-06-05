package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.ItemInstance;
import com.skinmarket.project.model.entity.enums.InstanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInstanceRepository extends JpaRepository<ItemInstance, Long> {
    @Query("Select ii FROM ItemInstance ii " +
            "WHERE ii.inventory.user.userId = :userId " +
            "AND ii.status = 'ININVENTORY'")
    List<ItemInstance> getInventoryByUserIdAndStatus(@Param("userId") Long userId);
}
