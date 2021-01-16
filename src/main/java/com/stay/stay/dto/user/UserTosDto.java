package com.stay.stay.dto.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTosDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate agreeDate;

    @Builder
    private UserTosDto(LocalDate agreeDate){
        this.agreeDate = agreeDate;
    }
}
