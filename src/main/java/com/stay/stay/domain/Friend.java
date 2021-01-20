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
    private String profileImage;
    private int currentRecord;
    private int bestRecord;
    private boolean isPrivate;


    @Builder
    private Friend(User user, String name, String profileImage, int currentRecord, int bestRecord, boolean isPrivate) {

        this.user = user;
        user.getFriendSet().add(this);
        this.name = name;
        this.profileImage = profileImage;
        this.currentRecord = currentRecord;
        this.bestRecord = bestRecord;
        this.isPrivate = isPrivate;
    }
}
