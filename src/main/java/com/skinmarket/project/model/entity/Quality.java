package com.skinmarket.project.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qualityId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private QualityName qualityName;


    @NotNull
    @Enumerated(EnumType.STRING)
    private QualityColor qualityColor;

    @OneToMany(mappedBy = "quality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<ItemDefinition> itemDefinition = new HashSet<>();

}
