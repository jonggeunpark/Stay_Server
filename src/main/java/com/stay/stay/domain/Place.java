package com.stay.stay.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PLACE")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    private String name;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Place(User user, String name, String address){

        this.user = user;
        user.getPlaceSet().add(this);

        this.name = name;
        this.address = address;

    }
}
