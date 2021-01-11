package com.stay.stay.service;

import com.stay.stay.domain.Place;
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
    private void savePlace(Place place) { placeRepository.save(place); }
}
