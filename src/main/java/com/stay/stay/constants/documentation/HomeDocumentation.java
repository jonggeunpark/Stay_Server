package com.stay.stay.constants.documentation;

import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.stay.stay.constants.ApiDocumentationUtils.getDocumentRequest;
import static com.stay.stay.constants.ApiDocumentationUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class HomeDocumentation {
    public static RestDocumentationResultHandler readHome() {
        return document("home/read-home",
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
                                .type(JsonFieldType.OBJECT).description("응답 데이터"),
                        fieldWithPath("data.corona.stateDt")
                                .type(JsonFieldType.STRING).description("기준 날짜"),
                        fieldWithPath("data.corona.decideCnt")
                                .type(JsonFieldType.STRING).description("확진자 수"),
                        fieldWithPath("data.corona.distancingNum")
                                .type(JsonFieldType.NUMBER).description("사회적 거리두기 단계"),
                        fieldWithPath("data.currentRecord")
                                .type(JsonFieldType.NUMBER).description("유저 현재 기록")
                )
        );
    }
}
