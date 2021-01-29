package com.stay.stay.controller;

import com.stay.stay.constants.documentation.NoticeDocumentation;
import com.stay.stay.dto.notice.NoticeDetailDto;
import com.stay.stay.dto.notice.NoticeDto;
import com.stay.stay.service.NoticeService;
import com.stay.stay.service.UserService;
import org.aspectj.weaver.ast.Not;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = NoticeController.class)
@AutoConfigureRestDocs
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;

    @MockBean
    private UserService userService;

    @Test
    public void 공지사항_목록조회() throws Exception {

        //given
        List<NoticeDto> response = new ArrayList<>();
        response.add(new NoticeDto(1L,"공지사항에 대해 안내드립니다.", LocalDate.of(2020,1,6)));
        response.add(new NoticeDto(2L,"공지사항에 대해 안내드립니다.2", LocalDate.of(2020,1,16)));


        given(noticeService.readNoticeAll(eq(1L))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/notice")
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(NoticeDocumentation.readNoticeAll());
    }

    @Test
    public void 공지사항_상세조회() throws Exception {

        //given
        NoticeDetailDto response = NoticeDetailDto.builder()
                .id(1L)
                .title("공지사항에 대해 안내드립니다.")
                .content("내용입니다.")
                .createdDate(LocalDate.of(2021,1,16))
                .build();

        given(noticeService.readNotice(eq(1L))).willReturn(response);

        //when
        ResultActions result = mockMvc.perform(get("/notice/{noticeIndex}",1L)
                .header("userIndex", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(NoticeDocumentation.readNotice());
    }
}
