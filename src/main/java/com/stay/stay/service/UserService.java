package com.stay.stay.service;

import com.stay.stay.domain.User;
import com.stay.stay.dto.user.UpdateTosDto;
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

    @Transactional
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    @Transactional
    public UpdateTosDto updateTosAgreeDate(User user){

        user.setTosAgreeDate(LocalDate.now());

        return UpdateTosDto.of(LocalDate.now());
    }
}
