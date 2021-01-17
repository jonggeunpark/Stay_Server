package com.stay.stay.dto.notice;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDetailDto {

    Long id;
    String title;
    String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate createdDate;

    @Builder
    public NoticeDetailDto(Long id, String title, String content, LocalDate createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
