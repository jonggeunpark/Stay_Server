package com.stay.stay.dto.notice;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeDto {
    Long id;
    String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate createdDate;

    @Builder
    public NoticeDto(Long id, String title, LocalDate createdDate) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
    }
}
