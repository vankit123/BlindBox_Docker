package com.msblindbox_se182744.controller;

import com.msblindbox_se182744.dto.BlindBoxDto;
import com.msblindbox_se182744.dto.request.BlindBoxRequest;
import com.msblindbox_se182744.service.BlindBoxService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blindBox")
@RequiredArgsConstructor
public class BlindBoxController {
        private final BlindBoxService blindBoxService;
    @GetMapping
    public ResponseEntity<List<BlindBoxDto>> getAllBlindBoxes() {
        return ResponseEntity.ok().body(blindBoxService.getAllBlindBoxes());
    }
    @DeleteMapping("/{blindBoxId}")
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<Void> deleteBlindBox(@PathVariable Integer blindBoxId) {
        blindBoxService.deleteBlindBox(blindBoxId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<BlindBoxDto> createBlindBox( @Valid @RequestBody  BlindBoxRequest request) {
        BlindBoxDto createdBlindBox = blindBoxService.createBlindBox(request);
        return ResponseEntity.ok(createdBlindBox);
    }

    @PutMapping("/{blindBoxId}")
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<BlindBoxDto> updateBlindBox(@PathVariable Integer blindBoxId, @Valid @RequestBody BlindBoxRequest request) {
        BlindBoxDto updatedBlindBox = blindBoxService.updateBlindBox(blindBoxId, request);
        return ResponseEntity.ok(updatedBlindBox);
    }

}
