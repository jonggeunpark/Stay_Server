package com.stay.stay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "NOTICE")
@Getter @Setter
public class Notice {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    private String content;

    @DateTimeFormat
    private LocalDate created_date;

    @Builder
    private Notice(String content, LocalDate created_date){
        this.content = content;
        this.created_date = created_date;
    }
}