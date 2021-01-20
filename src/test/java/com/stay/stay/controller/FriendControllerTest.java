package com.stay.stay.controller;

import com.stay.stay.constants.documentation.FriendDocumentation;
import com.stay.stay.constants.documentation.NoticeDocumentation;
import com.stay.stay.dto.friend.FriendDto;
import com.stay.stay.dto.friend.RankDto;
import com.stay.stay.dto.user.UserRankDto;
import com.stay.stay.service.FriendService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FriendController.class)
@AutoConfigureRestDocs
public class FriendControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FriendService friendService;

    @MockBean
    UserService userService;

    @Test
    public void 친구_목록_조회() throws Exception {

        //given
        UserRankDto userRankDto = UserRankDto.builder()
                .name("시연")
                .profileImage("link")
                .currentRecord(4)
                .build();

        List<FriendDto> friendDtoList = new ArrayList<>();
        friendDtoList.add(new FriendDto("종근","link", false, 5, 1));
        friendDtoList.add(new FriendDto("선영","link", false, 4, 2));
        friendDtoList.add(new FriendDto("민수","link", false, 2, 4));
        friendDtoList.add(new FriendDto("철수","link", true, 13, 0));

        RankDto response = RankDto.builder()
                .user(userRankDto)
                .friends(friendDtoList)
                .build();

        given(friendService.readFriendAll(eq(1L))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/friend")
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(FriendDocumentation.readFriendAll());
    }
}
