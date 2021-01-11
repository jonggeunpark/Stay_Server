package com.stay.stay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String profileImage;

    @DateTimeFormat
    private LocalDate tosAgreeDate;

    @Column(columnDefinition = "integer default 0")
    private int currentRecord;

    @Column(columnDefinition = "integer default 0")
    private int bestRecord;

    private boolean isPrivate;

    private boolean isCompliedToday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Place> placeSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Stamp> stampSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Friend> friendSet;

    @Builder
    private User(String name, String profileImage, LocalDate tosAgreeDate, int currentRecord, int bestRecord,
                 boolean isPrivate, boolean isCompliedToday, Set<Place> placeSet, Set<Stamp> stampSet, Set<Friend> friendSet) {

        this.name = name;
        this.profileImage = profileImage;
        this.tosAgreeDate = tosAgreeDate;
        this.currentRecord = currentRecord;
        this.bestRecord = bestRecord;
        this.isPrivate = isPrivate;
        this.isCompliedToday = isCompliedToday;
        this.placeSet = placeSet;
        this.stampSet = stampSet;
        this.friendSet = friendSet;
    }
}
