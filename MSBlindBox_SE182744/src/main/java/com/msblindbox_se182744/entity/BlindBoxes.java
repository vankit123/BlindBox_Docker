package com.msblindbox_se182744.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blind_boxes")
public class BlindBoxes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blindBoxId;

    private String name ;

    private String rarity;

    private Double price;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false )
    private BlindBoxCategories category;

}
