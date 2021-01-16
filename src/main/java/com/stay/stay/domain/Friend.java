package com.stay.stay.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FRIEND")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {

    @Id @GeneratedValue
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String profile_image;
    private int current_record;
    private int best_record;
    private boolean isPrivate;


    @Builder
    private Friend(User user, String name, String profile_image, int current_record, int best_record, boolean isPrivate) {

        this.user = user;
        user.getFriendSet().add(this);
        this.name = name;
        this.profile_image = profile_image;
        this.current_record = current_record;
        this.best_record = best_record;
        this.isPrivate = isPrivate;
    }
}
