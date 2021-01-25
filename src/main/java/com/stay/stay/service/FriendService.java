package com.stay.stay.service;

import com.stay.stay.domain.Friend;
import com.stay.stay.domain.User;
import com.stay.stay.dto.friend.FriendDto;
import com.stay.stay.dto.friend.RankDto;
import com.stay.stay.dto.user.UserRankDto;
import com.stay.stay.repository.FriendRepository;
import com.stay.stay.repository.RankInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserService userService;

    @Transactional
    private void saveFriend(Friend friend) { friendRepository.save(friend); }

    public RankDto readFriendAll(Long userId) {
        User user = userService.findById(userId);
        List<RankInterface> rankInterfaceList = friendRepository.findFriendByPrivateAndRecord(userId);
        List<FriendDto> friendDtoList = new ArrayList<>();
        for(RankInterface rankInterface: rankInterfaceList) {
            FriendDto friendDto = FriendDto.builder()
                    .name(rankInterface.getName())
                    .profileImage(rankInterface.getProfile_image())
                    .isPrivate(rankInterface.getIs_private())
                    .currentRecord(rankInterface.getCurrent_record())
                    .rank(rankInterface.getRanking())
                    .build();

            friendDtoList.add(friendDto);
        }

        UserRankDto userRankDto = UserRankDto.builder()
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .currentRecord(user.getCurrentRecord())
                .build();

        RankDto response = RankDto.builder()
                .user(userRankDto)
                .friends(friendDtoList)
                .build();

        return response;
    }
}
