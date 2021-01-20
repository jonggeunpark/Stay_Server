package com.stay.stay.dto.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRankDto {

    String name;
    String profileImage;
    int currentRecord;

    @Builder
    private UserRankDto(String name, String profileImage, int currentRecord) {
        this.name = name;
        this.profileImage = profileImage;
        this.currentRecord = currentRecord;
    }
}
