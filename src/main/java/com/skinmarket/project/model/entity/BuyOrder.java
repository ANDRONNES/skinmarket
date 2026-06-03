package com.skinmarket.project.model.entity;

import com.skinmarket.project.model.entity.enums.BuyOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyOrderId;

    @NotNull
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


    private LocalDateTime closedAt;

    @NotNull
    private BigDecimal targetPrice;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BuyOrderStatus buyOrderStatus = BuyOrderStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_definition_id", nullable = false)
    private ItemDefinition itemDefinition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User buyer;

    @OneToOne(mappedBy = "buyOrder", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Transaction transaction;
}
