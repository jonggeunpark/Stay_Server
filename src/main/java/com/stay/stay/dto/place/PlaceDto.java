package com.stay.stay.dto.place;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceDto {

    Long id;
    String name;
    String address;

    @Builder
    public PlaceDto(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
