package com.stay.stay.service;

import com.stay.stay.domain.Place;
import com.stay.stay.domain.User;
import com.stay.stay.dto.place.PlaceDto;
import com.stay.stay.dto.place.PlaceIdDto;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.exception.NotFoundException;
import com.stay.stay.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final UserService userService;

    @Transactional
    public void savePlace(Place place) { placeRepository.save(place); }

    public Place findById(Long placeId) { return placeRepository.findById(placeId).orElseThrow(() -> new NotFoundException("Place Not Found")); }


    @Transactional
    public PlaceIdDto createPlace(Long userId , PlaceRequest placeRequest) {

        User user = userService.findById(userId);

        Place newPlace = Place.builder()
                .user(user)
                .name(placeRequest.getName())
                .address(placeRequest.getAddress())
                .build();

        savePlace(newPlace);

        PlaceIdDto response = PlaceIdDto.builder().id(newPlace.getId()).build();
        return response;
    }

    public List<PlaceDto> readPlaceAll(Long userId) {
        User user = userService.findById(userId);

        List<PlaceDto> response = new ArrayList<>();

        for(Place place: user.getPlaceSet()) {
            PlaceDto placeDto = PlaceDto.builder()
                    .id(place.getId())
                    .name(place.getName())
                    .address(place.getAddress())
                    .build();

            response.add(placeDto);
        }

        return response;
    }

    public PlaceDto readPlace(Long placeId) {

        Place place = findById(placeId);

        PlaceDto response = PlaceDto.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .build();

        return response;
    }

    @Transactional
    public PlaceDto updatePlace(Long userId, PlaceDto placeDto) {
        User user = userService.findById(userId);
        Place place = findById(placeDto.getId());

        place.setName(placeDto.getName());
        place.setAddress(placeDto.getAddress());
        savePlace(place);

        PlaceDto response = PlaceDto.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .build();

        return response;
    }
}
