package com.stay.stay.controller;

import com.stay.stay.constants.documentation.FriendDocumentation;
import com.stay.stay.constants.documentation.HomeDocumentation;
import com.stay.stay.dto.corona.CoronaDto;
import com.stay.stay.dto.home.HomeDto;
import com.stay.stay.service.HomeService;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
@AutoConfigureRestDocs
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    HomeService homeService;

    @Test
    public void 홈_화면_조회() throws Exception {

        //given
        CoronaDto coronaDto = CoronaDto.builder()
                .stateDt("20210125")
                .decideCnt("75521")
                .distancingNum(2)
                .build();

        HomeDto response = HomeDto.builder()
                .corona(coronaDto)
                .currentRecord(4)
                .build();

        given(homeService.readHome(eq(1L))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/home")
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(HomeDocumentation.readHome());
    }
}
