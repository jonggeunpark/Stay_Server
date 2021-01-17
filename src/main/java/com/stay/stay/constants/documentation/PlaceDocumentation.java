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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class PlaceDocumentation {
    public static RestDocumentationResultHandler createPlace() {
        return document("place/create-place",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
                ),
                requestFields(
                        fieldWithPath("name")
                                .type(JsonFieldType.STRING).description("장소 이름"),
                        fieldWithPath("address")
                                .type(JsonFieldType.STRING).description("장소 주소")
                        ),
                responseFields(
                        fieldWithPath("status")
                                .type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message")
                                .type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data")
                                .type(JsonFieldType.OBJECT).description("응답 데이터"),
                        fieldWithPath("data.id")
                                .type(JsonFieldType.NUMBER).description("장소 id")
                )
        );
    }

    public static RestDocumentationResultHandler readPlaceAll() {
        return document("place/read-place-all",
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
                                .type(JsonFieldType.NUMBER).description("장소 id"),
                        fieldWithPath("data[].name")
                                .type(JsonFieldType.STRING).description("장소 이름"),
                        fieldWithPath("data[].address")
                                .type(JsonFieldType.STRING).description("장소 주소")
                )
        );
    }

    public static RestDocumentationResultHandler readPlace() {
        return document("place/read-place",
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
                        fieldWithPath("data.id")
                                .type(JsonFieldType.NUMBER).description("장소 id"),
                        fieldWithPath("data.name")
                                .type(JsonFieldType.STRING).description("장소 이름"),
                        fieldWithPath("data.address")
                                .type(JsonFieldType.STRING).description("장소 주소")
                )
        );
    }

    public static RestDocumentationResultHandler updatePlace() {
        return document("place/update-place",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
                ),
                requestFields(
                        fieldWithPath("id")
                                .type(JsonFieldType.NUMBER).description("변경할 장소 id"),
                        fieldWithPath("name")
                                .type(JsonFieldType.STRING).description("변경할 장소 이름"),
                        fieldWithPath("address")
                                .type(JsonFieldType.STRING).description("변경할 장소 주소")
                ),
                responseFields(
                        fieldWithPath("status")
                                .type(JsonFieldType.NUMBER).description("응답 코드"),
                        fieldWithPath("message")
                                .type(JsonFieldType.STRING).description("응답 메세지"),
                        fieldWithPath("data")
                                .type(JsonFieldType.OBJECT).description("응답 데이터"),
                        fieldWithPath("data.id")
                                .type(JsonFieldType.NUMBER).description("장소 id"),
                        fieldWithPath("data.name")
                                .type(JsonFieldType.STRING).description("장소 이름"),
                        fieldWithPath("data.address")
                                .type(JsonFieldType.STRING).description("장소 주소")
                )
        );
    }

    public static RestDocumentationResultHandler deletePlace() {
        return document("place/delete-place",
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
                                .type(JsonFieldType.NULL).description("응답 데이터")
                )
        );
    }
}
