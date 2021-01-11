package com.stay.stay.service;

import com.stay.stay.domain.User;
import com.stay.stay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    private void saveUser(User user) {userRepository.save(user);}
}