package com.stay.stay.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateTosDto {

    private LocalDate agreeDate;

    public static UpdateTosDto of(LocalDate agreeDate){
        return new UpdateTosDto(agreeDate);
    }
}
