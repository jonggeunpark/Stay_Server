package com.stay.stay.service;

import com.stay.stay.domain.Stamp;
import com.stay.stay.domain.User;
import com.stay.stay.dto.stamp.StampDto;
import com.stay.stay.repository.CalendarInterface;
import com.stay.stay.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;
    private final UserService userService;

    @Transactional
    public void saveStamp(Stamp stamp) { stampRepository.save(stamp); }

    public List<StampDto> readStampForCalendar(Long userId, int year, int month) {
        User user = userService.findById(userId);
        List<StampDto> response = new ArrayList<>();
        List<CalendarInterface> calendarInterfaceList = stampRepository.findStampForCalendar(userId, year, month);

        for(CalendarInterface calendarInterface :calendarInterfaceList){

            Boolean get = false;
            if(calendarInterface.getSuccess() == 1){
                get = true;
            }
            StampDto stampDto = StampDto.builder()
                    .date(calendarInterface.getD())
                    .get(get)
                    .build();

            response.add(stampDto);
        }

        return response;
    }

}
