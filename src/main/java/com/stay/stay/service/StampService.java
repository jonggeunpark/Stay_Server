package com.stay.stay.service;

import com.stay.stay.domain.Stamp;
import com.stay.stay.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;

    @Transactional
    private void saveStamp(Stamp stamp) { stampRepository.save(stamp); }

}
