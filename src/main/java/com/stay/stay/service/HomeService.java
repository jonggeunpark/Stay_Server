package com.stay.stay.service;

import com.stay.stay.domain.Corona;
import com.stay.stay.domain.User;
import com.stay.stay.dto.corona.CoronaDto;
import com.stay.stay.dto.home.HomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {

    private final UserService userService;
    private final CoronaService coronaService;

    public HomeDto readHome(Long userId) {

        User user = userService.findById(userId);
        Corona corona = coronaService.findById(1L);

        CoronaDto coronaDto = CoronaDto.builder()
                .stateDt(corona.getStateDt())
                .decideCnt(corona.getDecideCnt())
                .distancingNum(corona.getDistancingNum())
                .build();

        HomeDto response = HomeDto.builder()
                .currentRecord(user.getCurrentRecord())
                .corona(coronaDto)
                .build();

        return response;
    }

}
