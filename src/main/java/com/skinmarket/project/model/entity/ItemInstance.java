package com.skinmarket.project.model.entity;

import com.skinmarket.project.model.entity.enums.InstanceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemInstanceId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InstanceStatus status = InstanceStatus.ININVENTORY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_definition_id", nullable = false)
    private ItemDefinition itemDefinition;

    @OneToOne(mappedBy = "itemInstance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Listing activeListing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @OneToMany(mappedBy = "itemInstance", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Transaction> transactionList = new HashSet<>();

}
