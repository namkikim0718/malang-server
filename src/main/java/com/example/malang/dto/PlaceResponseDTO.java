package com.example.malang.dto;

import com.example.malang.domain.Address;
import com.example.malang.domain.Place;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlaceResponseDTO {

    private Long id;
    private String name;
    private String lat;
    private String lng;
    private String city;
    private String street;
    private String zipcode;

    @Builder
    public PlaceResponseDTO(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.lat = place.getLat();
        this.lng = place.getLng();
        this.city = place.getAddress().getCity();
        this.street = place.getAddress().getStreet();
        this.zipcode = place.getAddress().getZipcode();
    }
}
