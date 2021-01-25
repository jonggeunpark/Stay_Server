package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.Corona;
import com.stay.stay.domain.User;
import com.stay.stay.dto.corona.CoronaDto;
import com.stay.stay.dto.home.HomeDto;
import com.stay.stay.service.CoronaService;
import com.stay.stay.service.HomeService;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpResponse;

@Controller
@RequestMapping(path = "/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<Message> home(@RequestHeader("userIndex") Long userId){

        User user = userService.findById(userId);

        HomeDto response = homeService.readHome(userId);
        Message message = new Message(StatusCode.OK, ResponseMessage.Read_home, response);
        return new ResponseEntity<>(message ,HttpStatus.OK);
    }
}
