package com.stay.stay.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRankDto {

    String name;
    int currentRecord;
    int rank;

    @Builder
    private UserRankDto(String name, int currentRecord, int rank) {
        this.name = name;
        this.currentRecord = currentRecord;
        this.rank = rank;
    }
}
