package com.dongyang.core.api.favorite.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class FavoriteInfoDto {

    private final Long favoriteId;
    private final String question;
    private final String answer;
}
