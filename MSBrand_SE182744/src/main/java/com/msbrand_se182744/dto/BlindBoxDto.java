package com.msbrand_se182744.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BlindBoxDto {
    private String brandName;

    private Integer blindBoxId;

    private String name ;

    private String rarity;

    private Double price;
    private LocalDate releaseDate;

    private Integer stock;


}

