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

    private String profile_image;

    @DateTimeFormat
    private LocalDate terms_allow_date;

    @Column(columnDefinition = "integer default 0")
    private int current_record;

    @Column(columnDefinition = "integer default 0")
    private int best_record;

    private boolean isPrivate;

    private boolean isCompliedToday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Place> placeSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Stamp> stampSet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Friend> friendSet;

    @Builder
    private User(String name, String profile_image, LocalDate terms_allow_date, int current_record, int best_record,
                 boolean isPrivate, boolean isCompliedToday, Set<Place> placeSet, Set<Stamp> stampSet, Set<Friend> friendSet) {

        this.name = name;
        this.profile_image = profile_image;
        this.terms_allow_date = terms_allow_date;
        this.current_record = current_record;
        this.best_record = best_record;
        this.isPrivate = isPrivate;
        this.isCompliedToday = isCompliedToday;
        this.placeSet = placeSet;
        this.stampSet = stampSet;
        this.friendSet = friendSet;
    }
}
