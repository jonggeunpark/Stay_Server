package com.stay.stay.dto.friend;

import com.stay.stay.dto.user.UserRankDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankDto {

    UserRankDto user;
    List<FriendDto> friends;

    @Builder
    private RankDto(UserRankDto user, List<FriendDto> friends) {
        this.user = user;
        this.friends = friends;
    }
}
