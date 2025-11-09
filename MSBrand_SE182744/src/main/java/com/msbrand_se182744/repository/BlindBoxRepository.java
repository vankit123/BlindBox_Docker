package com.msbrand_se182744.repository;

import com.msbrand_se182744.entity.BlindBoxes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlindBoxRepository extends JpaRepository<BlindBoxes, Integer> {
    void deleteByBlindBoxId(Integer blindBoxId);
}
