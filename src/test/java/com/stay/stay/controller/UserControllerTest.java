package com.stay.stay.controller;

import com.stay.stay.constants.documentation.UserDocumentation;
import com.stay.stay.domain.User;
import com.stay.stay.dto.user.UserPrivacyDto;
import com.stay.stay.dto.user.UserTosDto;
import com.stay.stay.service.UserService;
import org.apache.tomcat.jni.Local;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureRestDocs
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public User createUser() {
        User user = User.builder()
                .id(1L)
                .name("민수")
                .profileImage(null)
                .tosAgreeDate(null)
                .stampSet(null)
                .placeSet(null)
                .friendSet(null)
                .isPrivate(true)
                .isCompliedToday(true)
                .currentRecord(0)
                .bestRecord(0)
                .build();

        return user;
    }
    @Test
    public void 약관_동의() throws Exception {

        //given
        User user = createUser();

        UserTosDto response = UserTosDto.builder()
                .agreeDate(LocalDate.now())
                .build();

        given(userService.updateTosAgreeDate(any())).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(put("/user/terms")
                .header("userIndex", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(UserDocumentation.updateTos());
    }

    @Test
    public void 공개_여부_변경() throws Exception {

        //given
        User user = createUser();
        UserPrivacyDto userPrivacyDto = UserPrivacyDto.builder()
                .isPrivate(false)
                .build();

        given(userService.updatePrivacy(any())).willReturn(userPrivacyDto);

        //when
        ResultActions result = mockMvc.perform(put("/user/privacy")
                .header("userIndex", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(UserDocumentation.updatePrivacy());
    }
}
