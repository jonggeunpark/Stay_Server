package com.stay.stay.dto.friend;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendDto {

    String name;
    String profileImage;
    Boolean isPrivate;
    Integer currentRecord;
    Integer rank;

    @Builder
    public FriendDto(String name, String profileImage, Boolean isPrivate, Integer currentRecord, Integer rank) {
        this.name = name;
        this.profileImage = profileImage;
        this.isPrivate = isPrivate;
        this.currentRecord = currentRecord;
        this.rank = rank;
    }
}
