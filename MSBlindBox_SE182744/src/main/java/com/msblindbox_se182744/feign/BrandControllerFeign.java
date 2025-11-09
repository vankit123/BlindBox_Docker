package com.msblindbox_se182744.feign;

import com.msblindbox_se182744.dto.BlindBoxDto;
import com.msblindbox_se182744.dto.request.BlindBoxRequest;

import jakarta.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "brand-service", url = "${app.brand-service-path}")
public interface BrandControllerFeign {

    @DeleteMapping("/brand/{blindBoxId}")
    ResponseEntity<Void> delete(@PathVariable Integer blindBoxId);
    @PostMapping("/brand")
    ResponseEntity<BlindBoxDto> createBlindBox(@RequestBody @Valid BlindBoxRequest request);
    @GetMapping("/brand")
    ResponseEntity<List<BlindBoxDto>> getAllBlindBoxes();
    @PutMapping("/brand/{blindBoxId}")
    ResponseEntity<BlindBoxDto> updateBlindBox(@PathVariable Integer blindBoxId, @RequestBody @Valid BlindBoxRequest request);
}
