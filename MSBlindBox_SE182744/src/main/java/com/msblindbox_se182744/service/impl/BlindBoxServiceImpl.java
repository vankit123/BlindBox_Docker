package com.msblindbox_se182744.service.impl;

import com.msblindbox_se182744.dto.BlindBoxDto;
import com.msblindbox_se182744.dto.request.BlindBoxRequest;
import com.msblindbox_se182744.entity.BlindBoxes;
import com.msblindbox_se182744.feign.BrandControllerFeign;
import com.msblindbox_se182744.repository.BlindBoxRepository;
import com.msblindbox_se182744.repository.CategoryRepository;
import com.msblindbox_se182744.service.BlindBoxService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BlindBoxServiceImpl implements BlindBoxService {
    private final BlindBoxRepository blindBoxRepository;
    private final CategoryRepository categoryRepository;
    private final BrandControllerFeign brandControllerFeign;




    @Override
    public List<BlindBoxDto> getAllBlindBoxes() {
        List<BlindBoxes> blindBoxDtoList = blindBoxRepository.findAll();
        List<BlindBoxDto> blindBoxDtos= brandControllerFeign.getAllBlindBoxes().getBody();
        return blindBoxDtoList.stream().map(blindBox -> BlindBoxDto.builder()
                .blindBoxId(blindBox.getBlindBoxId())
                .brandName(blindBoxDtos.stream()
                        .filter(dto -> dto.getBlindBoxId().equals(blindBox.getBlindBoxId()))
                        .findFirst()
                        .map(BlindBoxDto::getBrandName)
                        .orElse(null))
                .name(blindBox.getName())
                .rarity(blindBox.getRarity())
                .price(blindBox.getPrice())
                .releaseDate(blindBox.getReleaseDate())
                .stock(blindBox.getStock())
                .categoryName(blindBox.getCategory().getCategoryName())
                .build()).toList();
    }

    @Transactional
    @Override
    public void deleteBlindBox(Integer blindBoxId) {
        try{
            BlindBoxes blindBox = blindBoxRepository.findById(blindBoxId)
                    .orElseThrow(() -> new RuntimeException("Blind box with ID " + blindBoxId + " not found"));

            blindBoxRepository.delete(blindBox);
            brandControllerFeign.delete(blindBoxId);
        }catch (Exception e) {
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
            blindBoxes.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found")));
            BlindBoxes savedBlindBox = blindBoxRepository.save(blindBoxes);
            BlindBoxDto blindBoxDto= brandControllerFeign.createBlindBox(request).getBody();
            return BlindBoxDto.builder()
                    .blindBoxId(savedBlindBox.getBlindBoxId())
                    .name(savedBlindBox.getName())
                    .rarity(savedBlindBox.getRarity())
                    .price(savedBlindBox.getPrice())
                    .releaseDate(savedBlindBox.getReleaseDate())
                    .stock(savedBlindBox.getStock())
                    .brandName(blindBoxDto != null ? blindBoxDto.getBrandName() : null)
                    .categoryName(savedBlindBox.getCategory().getCategoryName())
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public BlindBoxDto updateBlindBox(Integer blindBoxId, BlindBoxRequest request) {
        try{
         BlindBoxes blindBoxes = blindBoxRepository.findById(blindBoxId).orElseThrow(()-> new RuntimeException("Blind box not found"));
            blindBoxes.setName(request.getName());
            blindBoxes.setRarity(request.getRarity());
            blindBoxes.setPrice(request.getPrice());
            blindBoxes.setStock(request.getStock());
            blindBoxes.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found")));
            BlindBoxes updatedBlindBox = blindBoxRepository.save(blindBoxes);
            BlindBoxDto blindBoxDto=  brandControllerFeign.updateBlindBox(blindBoxId, request).getBody();

            return BlindBoxDto.builder()
                    .blindBoxId(updatedBlindBox.getBlindBoxId())
                    .name(updatedBlindBox.getName())
                    .rarity(updatedBlindBox.getRarity())
                    .price(updatedBlindBox.getPrice())
                    .releaseDate(updatedBlindBox.getReleaseDate())
                    .stock(updatedBlindBox.getStock())
                    .brandName(blindBoxDto != null ? blindBoxDto.getBrandName() : null)
                    .categoryName(updatedBlindBox.getCategory().getCategoryName())
                    .build();
        }catch (Exception e){
            throw new RuntimeException("Blind box not found");
        }
    }
}
