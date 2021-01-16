package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.user.UserPrivacyDto;
import com.stay.stay.dto.user.UserTosDto;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    /** 약관 동의 날짜 변경 */
    @PutMapping(value = "/terms", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updateTermsAgreeDate(@RequestHeader("userIndex") Long userId) {
        User user = userService.findById(userId);
        UserTosDto response = userService.updateTosAgreeDate(user);

        Message message = new Message(StatusCode.OK, ResponseMessage.Update_Tos_Agree_Date, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /** 기록 공개 여부 변경 */
    @PutMapping(value = "/privacy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> updatePrivacy(@RequestHeader("userIndex") Long userId) {
        User user = userService.findById(userId);
        UserPrivacyDto response = userService.updatePrivacy(user);

        Message message = new Message(StatusCode.OK, ResponseMessage.Update_privacy, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
