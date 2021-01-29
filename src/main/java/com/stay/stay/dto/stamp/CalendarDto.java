package com.stay.stay.dto.stamp;

import com.stay.stay.dto.user.UserRecordDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CalendarDto {

    UserRecordDto record;
    List<StampDto> stamp;

    @Builder
    private CalendarDto(UserRecordDto record, List<StampDto> stamp) {
        this.record = record;
        this.stamp = stamp;
    }
}
