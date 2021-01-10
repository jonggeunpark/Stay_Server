package com.stay.stay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STAMP")
@Getter @Setter
public class Stamp {

    @Id @GeneratedValue
    @Column(name = "stamp_id")
    private Long id;

    @DateTimeFormat
    private LocalDate acquired_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Stamp(LocalDate acquired_date, User user){

        this.user = user;
        user.getStampSet().add(this);

        this.acquired_date = acquired_date;
    }
}
