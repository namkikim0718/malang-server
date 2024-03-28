package com.example.malang.service;

import com.example.malang.domain.Place;
import com.example.malang.dto.PlaceResponseDTO;
import com.example.malang.exception.BaseException;
import com.example.malang.exception.ErrorCode;
import com.example.malang.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceResponseDTO convertToDTO(Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_PLACE));

        return new PlaceResponseDTO(place);
    }
}
