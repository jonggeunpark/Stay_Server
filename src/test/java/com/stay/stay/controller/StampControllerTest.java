package com.stay.stay.controller;

import com.stay.stay.constants.documentation.FriendDocumentation;
import com.stay.stay.constants.documentation.StampDocumentation;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.dto.stamp.CalendarDto;
import com.stay.stay.dto.stamp.StampDto;
import com.stay.stay.dto.user.UserRecordDto;
import com.stay.stay.service.StampService;
import com.stay.stay.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StampController.class)
@AutoConfigureRestDocs
public class StampControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private StampService stampService;

    @Test
    public void 스탬프_월별_조회() throws Exception {

        //given
        List<StampDto> stampDtoList = new ArrayList<>();
        stampDtoList.add(new StampDto(Date.valueOf(LocalDate.of(2021,1,1)), true));
        stampDtoList.add(new StampDto(Date.valueOf(LocalDate.of(2021,1,2)), false));
        stampDtoList.add(new StampDto(Date.valueOf(LocalDate.of(2021,1,3)), true));

        UserRecordDto userRecordDto = UserRecordDto.builder()
                .currentRecord(4)
                .bestRecord(5)
                .build();

        CalendarDto response = CalendarDto.builder()
                .record(userRecordDto)
                .stamp(stampDtoList)
                .build();

        given(stampService.readStampForCalendar(eq(1L), eq(2020), eq(1))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/stamp/{year}/{month}", 2020, 1)
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(StampDocumentation.readStampForCalendar());
    }
}
