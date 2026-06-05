package com.skinmarket.project.model.entity;

import com.skinmarket.project.model.entity.enums.ListingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingId;

    @NotNull
    @DecimalMin(value = "0.04", inclusive = true, message = "Price cannot be smaller that 0.04 cents")
    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;

    @NotNull
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Transient
    public static BigDecimal marketFee = new BigDecimal(0.04);

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ListingStatus status = ListingStatus.ACTIVE;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<Favourite> favouriteList = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_instance_id", unique = true, nullable = false)
    private ItemInstance itemInstance;

    @OneToOne(mappedBy = "listing", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Transaction transaction;

    public static BigDecimal getFinalAmountWithFee(BigDecimal price){
        return price.multiply(BigDecimal.ONE.subtract(marketFee));
    }

}
