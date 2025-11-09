package com.msblindbox_se182744.repository;

import com.msblindbox_se182744.entity.BlindBoxCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<BlindBoxCategories, Integer> {
}
