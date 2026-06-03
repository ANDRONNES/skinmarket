package com.skinmarket.project.model.entity;

import com.skinmarket.project.model.entity.enums.TransactionStatus;
import com.skinmarket.project.model.entity.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_instance_id", nullable = false)
    private ItemInstance itemInstance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", unique = true, nullable = true)
    private Listing listing;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buy_order_id", unique = true, nullable = true)
    private BuyOrder buyOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;
}
