package com.stay.stay.dto.user;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPrivacyDto {

    private boolean isPrivate;

    @Builder
    private UserPrivacyDto(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}
