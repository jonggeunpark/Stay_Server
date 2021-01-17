package com.stay.stay.dto.place;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceIdDto {
    Long id;

    @Builder
    public PlaceIdDto(Long id){
        this.id = id;
    }
}
