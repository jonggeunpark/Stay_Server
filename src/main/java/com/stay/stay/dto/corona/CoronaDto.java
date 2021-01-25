package com.stay.stay.dto.corona;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoronaDto {

    String stateDt;
    String decideCnt;
    int distancingNum;

    @Builder
    private CoronaDto(String stateDt, String decideCnt, int distancingNum) {
        this.stateDt = stateDt;
        this.decideCnt = decideCnt;
        this.distancingNum = distancingNum;
    }
}
