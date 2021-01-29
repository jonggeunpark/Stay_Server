package com.stay.stay.controller;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import com.stay.stay.domain.User;
import com.stay.stay.dto.notice.NoticeDetailDto;
import com.stay.stay.dto.notice.NoticeDto;
import com.stay.stay.dto.place.PlaceDto;
import com.stay.stay.service.NoticeService;
import com.stay.stay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/notice")
public class NoticeController {

    private final UserService userService;
    private final NoticeService noticeService;

    /** 공지사항 목록 조회 */
    @GetMapping()
    public ResponseEntity<Message> readNoticeAll(@RequestHeader("userIndex") Long userId) {
        User user = userService.findById(userId);
        List<NoticeDto> response = noticeService.readNoticeAll(userId);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_notice_all, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /** 공지사항 상세 조회 */
    @GetMapping("/{noticeIndex}")
    public ResponseEntity<Message> readNotice(@RequestHeader("userIndex") Long userId, @PathVariable("noticeIndex") Long noticeId) {
        NoticeDetailDto response = noticeService.readNotice(noticeId);

        Message message = new Message(StatusCode.OK, ResponseMessage.Read_notice, response);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
