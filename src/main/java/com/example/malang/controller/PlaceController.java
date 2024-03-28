package com.example.malang.controller;

import com.example.malang.config.BaseResponse;
import com.example.malang.dto.PlaceResponseDTO;
import com.example.malang.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/places/{placeId}")
    public ResponseEntity<BaseResponse<PlaceResponseDTO>> findPlaceById(@PathVariable("placeId") Long placeId) {
        return ResponseEntity.ok().body(new BaseResponse<>(placeService.convertToDTO(placeId)));
    }
}
