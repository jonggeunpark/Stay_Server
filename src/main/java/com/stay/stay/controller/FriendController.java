package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.friend.FriendDto;
import com.stay.stay.dto.friend.RankDto;
import com.stay.stay.dto.notice.NoticeDto;
import com.stay.stay.service.FriendService;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final UserService userService;


    /** 친구 목록 조회 */
    @GetMapping()
    public ResponseEntity<Message> readFriendAll(@RequestHeader("userIndex") Long userId) {
        User user = userService.findById(userId);
        RankDto response = friendService.readFriendAll(userId);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_friend_all, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
