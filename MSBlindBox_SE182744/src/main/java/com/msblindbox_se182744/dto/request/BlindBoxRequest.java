package com.msblindbox_se182744.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BlindBoxRequest {
    @NotNull
    @Size(min = 10, message = "Name must be at least 10 characters long")
    private String name ;

    @NotNull
    @Min(value = 1, message = "Stock must be at least 1")
    @Max(value = 100, message = "Stock must be at most 5")
    private Integer stock;

    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    private Double price;
    @NotNull
    private String rarity;
    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer brandId;

}
