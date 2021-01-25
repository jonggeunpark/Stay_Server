package com.stay.stay.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRecordDto {

    int currentRecord;
    int bestRecord;

    @Builder
    private UserRecordDto(int currentRecord, int bestRecord){
        this.currentRecord = currentRecord;
        this.bestRecord = bestRecord;
    }
}
