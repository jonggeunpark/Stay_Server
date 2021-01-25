package com.stay.stay.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Corona")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Corona {

    @Id @GeneratedValue
    @Column(name = "corona_id")
    private Long id;

    private String STATE_DT;
    private String STATE_TIME;
    private int DECIDE_CNT;
    private int DISTANCING_NUM;

    @Builder
    public Corona(String STATE_DT, String STATE_TIME, int DECIDE_CNT, int DISTANCING_NUM) {
        this.STATE_DT = STATE_DT;
        this.STATE_TIME = STATE_TIME;
        this.DECIDE_CNT = DECIDE_CNT;
        this.DISTANCING_NUM = DISTANCING_NUM;
    }
}
