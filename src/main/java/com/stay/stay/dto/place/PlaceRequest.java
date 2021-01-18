package com.stay.stay.dto.place;

import com.stay.stay.domain.Place;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PlaceRequest {

    String name;
    String address;
}
