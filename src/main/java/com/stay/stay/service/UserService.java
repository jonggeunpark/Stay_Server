package com.stay.stay.service;

import com.stay.stay.domain.User;
import com.stay.stay.dto.user.UserPrivacyDto;
import com.stay.stay.dto.user.UserTosDto;
import com.stay.stay.exception.NotFoundException;
import com.stay.stay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(User user) {userRepository.save(user);}

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    @Transactional
    public UserTosDto updateTosAgreeDate(User user){
        user.setTosAgreeDate(LocalDate.now());
        saveUser(user);

        return UserTosDto.builder()
                .agreeDate(user.getTosAgreeDate())
                .build();
    }

    @Transactional
    public UserPrivacyDto updatePrivacy(User user) {
        user.setPrivate(!user.isPrivate());
        saveUser(user);

        return UserPrivacyDto.builder()
                .isPrivate(user.isPrivate())
                .build();
    }
}
