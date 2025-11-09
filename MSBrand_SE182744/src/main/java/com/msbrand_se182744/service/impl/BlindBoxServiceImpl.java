package com.msbrand_se182744.service.impl;


import com.msbrand_se182744.dto.BlindBoxDto;
import com.msbrand_se182744.dto.request.BlindBoxRequest;
import com.msbrand_se182744.entity.BlindBoxes;
import com.msbrand_se182744.repository.BlindBoxRepository;
import com.msbrand_se182744.repository.BrandRepository;
import com.msbrand_se182744.service.BlindBoxService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class BlindBoxServiceImpl implements BlindBoxService {
    private final BlindBoxRepository blindBoxRepository;
    private final BrandRepository brandRepository;
    public BlindBoxDto toDto(BlindBoxes blindBoxes) {
        return BlindBoxDto.builder()
                .blindBoxId(blindBoxes.getBlindBoxId())
                .name(blindBoxes.getName())
                .rarity(blindBoxes.getRarity())
                .price(blindBoxes.getPrice())
                .releaseDate(blindBoxes.getReleaseDate())
                .stock(blindBoxes.getStock())
                .brandName(blindBoxes.getBrand().getBrandName())
                .build();
    }
    @Transactional
    @Override
    public void deleteBlindBox(Integer blindBoxId) {
        try{
            System.out.println("Deleting blind box with ID: " + blindBoxId);

            BlindBoxes blindBox = blindBoxRepository.findById(blindBoxId)
                    .orElseThrow(() -> new RuntimeException("Blind box with ID " + blindBoxId + " not found"));

            blindBoxRepository.delete(blindBox);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
    @Transactional
    @Override
    public BlindBoxDto createBlindBox(BlindBoxRequest request) {
        try{
            BlindBoxes blindBoxes = new BlindBoxes();
            blindBoxes.setName(request.getName());
            blindBoxes.setRarity(request.getRarity());
            blindBoxes.setPrice(request.getPrice());
            blindBoxes.setReleaseDate(LocalDate.now());
            blindBoxes.setStock(request.getStock());
            blindBoxes.setBrand(brandRepository.findById(request.getBrandId()).orElseThrow(()-> new RuntimeException("Brand not found")));
            BlindBoxes savedBlindBox = blindBoxRepository.save(blindBoxes);
            return toDto(savedBlindBox);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlindBoxDto> getAllBlindBoxes() {

        return blindBoxRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public BlindBoxDto updateBlindBox(Integer blindBoxId, BlindBoxRequest request) {
        try{
            BlindBoxes blindBoxes = blindBoxRepository.findById(blindBoxId).orElseThrow(()-> new RuntimeException("Blind box not found"));
            blindBoxes.setName(request.getName());
            blindBoxes.setRarity(request.getRarity());
            blindBoxes.setPrice(request.getPrice());
            blindBoxes.setStock(request.getStock());
            blindBoxes.setBrand(brandRepository.findById(request.getBrandId()).orElseThrow(()-> new RuntimeException("Brand not found")));
            BlindBoxes updatedBlindBox = blindBoxRepository.save(blindBoxes);
            return toDto(updatedBlindBox);
        }catch (Exception e){
            throw new RuntimeException("Blind box not found");
        }
    }
}
