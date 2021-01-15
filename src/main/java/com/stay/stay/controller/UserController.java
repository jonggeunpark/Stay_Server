package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.user.UpdateTosDto;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    /** 위치정보 수집 동의 */
    @PutMapping("/terms")
    public ResponseEntity<Message> updateTermsAgreeDate(@RequestHeader("userIndex") Long userId) {

        User user = userService.findById(userId);
        UpdateTosDto response = userService.updateTosAgreeDate(user);

        Message message = new Message(StatusCode.OK, ResponseMessage.Update_Tos_Agree_Date, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
