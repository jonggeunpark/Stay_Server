package com.stay.stay.service;

import com.stay.stay.domain.Place;
import com.stay.stay.domain.User;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public void savePlace(Place place) { placeRepository.save(place); }


    @Transactional
    public Long createPlace(User user, PlaceRequest placeRequest) {

        Place newPlace = Place.builder()
                .user(user)
                .name(placeRequest.getName())
                .address(placeRequest.getAddress())
                .build();

        savePlace(newPlace);

        return newPlace.getId();
    }
}
