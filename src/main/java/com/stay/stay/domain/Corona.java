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

    private String stateDt;
    private String decideCnt;
    private int distancingNum;

    @Builder
    public Corona(String stateDt, String decideCnt, int distancingNum) {
        this.stateDt = stateDt;
        this.decideCnt = decideCnt;
        this.distancingNum = distancingNum;
    }
}
