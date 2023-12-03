package com.dongyang.core.api.favorite.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class FavoriteFindResponse {

    public FavoriteFindResponse(List<FavoriteInfoDto> favoriteInfos) {
        this.favoriteInfos = favoriteInfos;
    }

    @Schema(description = "즐겨찾기 정보")
    private List<FavoriteInfoDto> favoriteInfos;
}
