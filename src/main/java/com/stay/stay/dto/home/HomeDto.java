package com.stay.stay.dto.home;

import com.stay.stay.dto.corona.CoronaDto;
import com.stay.stay.dto.user.UserRecordDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeDto {

    CoronaDto corona;
    int currentRecord;

    @Builder
    private HomeDto(CoronaDto corona, int currentRecord) {
        this.corona = corona;
        this.currentRecord = currentRecord;
    }
}
