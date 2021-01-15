package com.stay.stay.exception;

import com.stay.stay.constants.Message;
import com.stay.stay.constants.ResponseMessage;
import com.stay.stay.constants.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Message> NotFoundException(NotFoundException e) {
        Message message = new Message(StatusCode.BAD_REQUEST, ResponseMessage.NotFound);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
