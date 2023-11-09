package com.dongyang.core.domain.favorite.dto.response;

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
