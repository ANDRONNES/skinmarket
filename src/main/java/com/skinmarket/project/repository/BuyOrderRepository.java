package com.skinmarket.project.repository;

import com.skinmarket.project.model.entity.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long> {
    @Query("SELECT b FROM BuyOrder b WHERE b.itemDefinition.itemDefinitionId = :defId " +
            "AND b.buyOrderStatus = 'ACTIVE' AND b.targetPrice = (SELECT MAX(b2.targetPrice) " +
            "FROM BuyOrder b2 WHERE b2.itemDefinition.itemDefinitionId = :defId AND b2.buyOrderStatus = 'ACTIVE')")
    Optional<BuyOrder> findHighestActiveBuyOrder(@Param("defId") Long defId);
}
