package com.skinmarket.project.model.entity;

import com.skinmarket.project.model.entity.enums.ItemType;
import com.skinmarket.project.model.entity.enums.StickerType;
import com.skinmarket.project.model.entity.enums.WeaponType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ItemDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemDefinitionId;

    @NotBlank
    private String name;

    @URL
    private String imageUrl;

    @URL
    private String previewInGameLink;

    private Boolean patch;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    private WeaponType weaponType;

    @Enumerated(EnumType.STRING)
    private StickerType stickerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quality_id", nullable = false)
    private Quality quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_collection_id", nullable = false)
    private ItemCollection itemCollection;

    @OneToMany(mappedBy = "itemDefinition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<ItemInstance> itemInstanceList = new HashSet<>();

    @OneToMany(mappedBy = "itemDefinition", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<BuyOrder> buyOrderList = new HashSet<>();
}
