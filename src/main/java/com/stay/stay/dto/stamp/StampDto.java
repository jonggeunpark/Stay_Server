package com.stay.stay.dto.stamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StampDto {

    Date date;
    Boolean get;

    @Builder
    public StampDto(Date date, Boolean get){
        this.date = date;
        this.get = get;
    }
}
