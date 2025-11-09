package com.msbrand_se182744.service;


import com.msbrand_se182744.dto.BlindBoxDto;
import com.msbrand_se182744.dto.request.BlindBoxRequest;

import java.util.List;

public interface BlindBoxService {
    void deleteBlindBox(Integer blindBoxId);
    BlindBoxDto createBlindBox(BlindBoxRequest request);
    List<BlindBoxDto> getAllBlindBoxes();
    BlindBoxDto updateBlindBox(Integer blindBoxId, BlindBoxRequest request);
}
