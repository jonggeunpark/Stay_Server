package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.place.PlaceDto;
import com.stay.stay.dto.place.PlaceIdDto;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.service.PlaceService;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/place")
public class PlaceController {

    private final UserService userService;
    private final PlaceService placeService;

    /** 내 장소 추가 */
    @PostMapping()
    public ResponseEntity<Message> createPlace(@RequestHeader("userIndex") Long userId, @RequestBody PlaceRequest placeRequest) {
        User user = userService.findById(userId);
        PlaceIdDto response = placeService.createPlace(userId, placeRequest);

        Message message = new Message(StatusCode.OK, ResponseMessage.Create_place, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /** 내 장소 조회 */
    @GetMapping()
    public ResponseEntity<Message> readPlaceAll(@RequestHeader("userIndex") Long userId) {
        User user = userService.findById(userId);
        List<PlaceDto> response = placeService.readPlaceAll(userId);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_place_all, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /** 내 장소 상세조회 */
    @GetMapping("/{placeId}")
    public ResponseEntity<Message> readPlace(@RequestHeader("userIndex") Long userId, @PathVariable("placeId") Long placeId) {
        PlaceDto response = placeService.readPlace(placeId);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_place, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
