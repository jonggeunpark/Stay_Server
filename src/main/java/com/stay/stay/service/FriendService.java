package com.stay.stay.service;

import com.stay.stay.domain.Friend;
import com.stay.stay.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    @Transactional
    private void saveFriend(Friend friend) { friendRepository.save(friend); }
}
