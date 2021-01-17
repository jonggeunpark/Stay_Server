package com.stay.stay.dto.place;

import com.stay.stay.domain.Place;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceRequest {

    String name;
    String address;

    @Builder
    public PlaceRequest(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
