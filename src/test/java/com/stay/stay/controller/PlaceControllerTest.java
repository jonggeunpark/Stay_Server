package com.stay.stay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stay.stay.constants.documentation.PlaceDocumentation;
import com.stay.stay.domain.Place;
import com.stay.stay.domain.User;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.service.PlaceService;
import com.stay.stay.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PlaceController.class)
@AutoConfigureRestDocs
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private UserService userService;

    public User createUser() {
        User user = User.builder()
                .id(1L)
                .name("민수")
                .profileImage("")
                .tosAgreeDate(LocalDate.now())
                .stampSet(new HashSet<>())
                .placeSet(new HashSet<>())
                .friendSet(new HashSet<>())
                .isPrivate(true)
                .isCompliedToday(true)
                .currentRecord(0)
                .bestRecord(0)
                .build();

        return user;
    }

    public Place createPlace(User user) {
        Place place = Place.builder()
                .user(user)
                .name("학교")
                .address("서울특별시 중구")
                .build();

        return place;
    }

    @Test
    public void 내_장소_추가() throws Exception {

        //given
        User user = createUser();
        Long id = 1L;
        PlaceRequest request = PlaceRequest.builder().name("학교").address("서울특별시 중구").build();

        given(placeService.createPlace(user, request)).willReturn(id);

        //when
        ResultActions result = mockMvc.perform(post("/place")
                .header("userIndex", user.getId())
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(PlaceDocumentation.createPlace());
    }
}
