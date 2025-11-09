package com.msblindbox_se182744.service;

import com.msblindbox_se182744.dto.BlindBoxDto;
import com.msblindbox_se182744.dto.request.BlindBoxRequest;

import java.util.List;

public interface BlindBoxService {
    List<BlindBoxDto> getAllBlindBoxes();
    void deleteBlindBox(Integer blindBoxId);
    BlindBoxDto createBlindBox(BlindBoxRequest request);
    BlindBoxDto updateBlindBox(Integer blindBoxId, BlindBoxRequest request);
}
