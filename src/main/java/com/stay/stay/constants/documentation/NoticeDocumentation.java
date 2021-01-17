package com.stay.stay.constants.documentation;

import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultHandler;

import static com.stay.stay.constants.ApiDocumentationUtils.getDocumentRequest;
import static com.stay.stay.constants.ApiDocumentationUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class NoticeDocumentation {
    public static RestDocumentationResultHandler readNoticeAll() {
        return document("notice/read-notice-all",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
                ),
                responseFields(
                        fieldWithPath("status")
                                .type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message")
                                .type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data")
                                .type(JsonFieldType.ARRAY).description("응답 데이터"),
                        fieldWithPath("data[].id")
                                .type(JsonFieldType.NUMBER).description("공지사항 id"),
                        fieldWithPath("data[].title")
                                .type(JsonFieldType.STRING).description("공지사항 제목"),
                        fieldWithPath("data[].createdDate")
                                .type(JsonFieldType.STRING).description("공지사항 작성일")
                )
        );
    }
}
