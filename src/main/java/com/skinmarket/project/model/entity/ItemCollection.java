package com.skinmarket.project.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ItemCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCollectionId;

    @NotBlank
    private String name;

    private String description;

    @URL
    private String imageUrl;

    @OneToMany(mappedBy = "itemCollection")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Set<ItemDefinition> itemDefinitionList = new HashSet<>();
}
