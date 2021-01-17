package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.service.PlaceService;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

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
        Long response = placeService.createPlace(user, placeRequest);

        Message message = new Message(StatusCode.OK, ResponseMessage.Create_place, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
