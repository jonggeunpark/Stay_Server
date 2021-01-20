package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.stamp.StampDto;
import com.stay.stay.service.StampService;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/stamp")
@RequiredArgsConstructor
public class StampController {

    private final StampService stampService;
    private final UserService userService;


    @GetMapping("/{year}/{month}")
    public ResponseEntity<Message> readStampForMonth(@RequestHeader("userIndex") Long userId, @PathVariable("year") int year, @PathVariable("month") int month) {

        User user = userService.findById(userId);
        List<StampDto> response = stampService.readStampForCalendar(userId, year, month);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_stamp_for_calendar, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
