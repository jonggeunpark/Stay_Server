package com.stay.stay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stay.stay.constants.documentation.PlaceDocumentation;
import com.stay.stay.domain.Place;
import com.stay.stay.domain.User;
import com.stay.stay.dto.place.PlaceDto;
import com.stay.stay.dto.place.PlaceIdDto;
import com.stay.stay.dto.place.PlaceRequest;
import com.stay.stay.service.PlaceService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    public Place createPlace(User user, String name, String address) {
        Place place = Place.builder()
                .user(user)
                .name(name)
                .address(address)
                .build();

        return place;
    }

    @Test
    public void 내_장소_추가() throws Exception {

        //given
        User user = createUser();
        PlaceIdDto response = PlaceIdDto.builder()
                .id(4L)
                .build();

        given(placeService.createPlace(eq(1L), any(PlaceRequest.class))).willReturn(response);

        PlaceRequest request = new PlaceRequest();
        request.setName("학교");
        request.setAddress("서울특별시 중구");


        //when
        ResultActions result = mockMvc.perform(post("/place")
                .header("userIndex", 1L)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(PlaceDocumentation.createPlace());
    }

    @Test
    public void 내_장소_조회() throws Exception {

        //given
        List<PlaceDto> response = new ArrayList<>();
        response.add(new PlaceDto(1L,"학교","서울특별시 중구"));
        response.add(new PlaceDto(2L,"집","서울특별시 동대문구"));


        given(placeService.readPlaceAll(eq(1L))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/place")
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(PlaceDocumentation.readPlaceAll());
    }

    @Test
    public void 내_장소_상세조회() throws Exception {

        //given
        PlaceDto response = PlaceDto.builder()
                .id(4L)
                .name("학교")
                .address("서울특별시 중구")
                .build();

        given(placeService.readPlace(eq(1L))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/place/{placeIndex}", 1L)
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(PlaceDocumentation.readPlace());
    }

    @Test
    public void 내_장소_변경() throws Exception {

        //given
        PlaceDto request = PlaceDto.builder()
                .id(1L)
                .name("학교")
                .address("서울특별시 중구")
                .build();

        PlaceDto response = PlaceDto.builder()
                .id(1L)
                .name("학교")
                .address("서울특별시 중구")
                .build();

        given(placeService.updatePlace(eq(1L), any(PlaceDto.class))).willReturn(response);


        //when
        ResultActions result = mockMvc.perform(put("/place")
                .header("userIndex", 1L)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(PlaceDocumentation.updatePlace());
    }

    @Test
    public void 내_장소_삭제() throws Exception {

        //given

        //when
        ResultActions result = mockMvc.perform(delete("/place")
                .header("userIndex", 1L)
                .header("placeIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(PlaceDocumentation.deletePlace());
    }
}
