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

public class FriendDocumentation {
    public static RestDocumentationResultHandler readFriendAll() {
        return document("friend/read-friend-all",
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
                        fieldWithPath("data.user")
                                .type(JsonFieldType.OBJECT).description("유저 데이터"),
                        fieldWithPath("data.user.name")
                                .type(JsonFieldType.STRING).description("유저 이름"),
                        fieldWithPath("data.user.currentRecord")
                                .type(JsonFieldType.NUMBER).description("유저 기록"),
                        fieldWithPath("data.user.rank")
                                .type(JsonFieldType.NUMBER).description("유저 순위"),
                        fieldWithPath("data.friends[].name")
                                .type(JsonFieldType.STRING).description("친구 이름"),
                        fieldWithPath("data.friends[].profileImage")
                                .type(JsonFieldType.STRING).description("친구 프로필 이미지 링크"),
                        fieldWithPath("data.friends[].isPrivate")
                                .type(JsonFieldType.BOOLEAN).description("친구 기록 비공개 여부"),
                        fieldWithPath("data.friends[].currentRecord")
                                .type(JsonFieldType.NUMBER).description("친구 기록"),
                        fieldWithPath("data.friends[].rank")
                                .type(JsonFieldType.NUMBER).description("친구 순위")
                )
        );
    }

}
