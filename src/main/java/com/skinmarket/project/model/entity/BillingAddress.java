package com.skinmarket.project.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long billingAddressId;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String street;

    @NotNull
    private Integer buildingNumber;

    private Integer apartmentNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "belongsTo_id", nullable = false, updatable = false)
    @NotNull
    private User belongsTo;

}
