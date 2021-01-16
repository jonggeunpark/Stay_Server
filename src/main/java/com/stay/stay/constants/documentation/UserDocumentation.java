package com.stay.stay.constants.documentation;

import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.stay.stay.constants.ApiDocumentationUtils.getDocumentRequest;
import static com.stay.stay.constants.ApiDocumentationUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class UserDocumentation {

    public static RestDocumentationResultHandler updateTos() {
        return document("user/tos-update",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
                ),
                responseFields(
                        fieldWithPath("status")
                                .type(JsonFieldType.NUMBER)
                                .description("응답 코드"),
                        fieldWithPath("message")
                                .type(JsonFieldType.STRING)
                                .description("응답 메세지"),
                        fieldWithPath("data")
                                .type(JsonFieldType.OBJECT)
                                .description("응답 데이터"),
                        fieldWithPath("data.agreeDate")
                                .type(JsonFieldType.STRING)
                                .description("동의 날짜")
                )
        );
    }

    public static RestDocumentationResultHandler updatePrivacy() {
        return document("user/privacy-update",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
                ),
                responseFields(
                        fieldWithPath("status")
                                .type(JsonFieldType.NUMBER)
                                .description("응답 코드"),
                        fieldWithPath("message")
                                .type(JsonFieldType.STRING)
                                .description("응답 메세지"),
                        fieldWithPath("data")
                                .type(JsonFieldType.OBJECT)
                                .description("응답 데이터"),
                        fieldWithPath("data.private")
                                .type(JsonFieldType.BOOLEAN)
                                .description("공개 여부")
                )
        );
    }
}
