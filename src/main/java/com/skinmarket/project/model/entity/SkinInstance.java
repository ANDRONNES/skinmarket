package com.skinmarket.project.model.entity;

import com.skinmarket.project.model.entity.enums.ExteriorType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@DiscriminatorValue("SKIN")
public class SkinInstance extends ItemInstance {
    @NotNull
    @Column(name = "skin_float")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "1.0", inclusive = false)
    private Float skinFloat;

    @NotNull
    @Min(1)
    @Max(1000)
    private Integer pattern;

    public ExteriorType getExterior() {
        if (this.skinFloat == null) {
            return null;
        }

        if (this.skinFloat >= 0.0f && this.skinFloat < 0.07f) {
            return ExteriorType.FN;
        } else if (this.skinFloat >= 0.07f && this.skinFloat < 0.15f) {
            return ExteriorType.MW;
        } else if (this.skinFloat >= 0.15f && this.skinFloat < 0.37f) {
            return ExteriorType.FT;
        } else if (this.skinFloat >= 0.37f && this.skinFloat < 0.44f) {
            return ExteriorType.WW;
        } else {
            return ExteriorType.BS;
        }
    }
}
