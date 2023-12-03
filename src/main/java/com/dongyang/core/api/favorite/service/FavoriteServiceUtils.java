package com.dongyang.core.api.favorite.service;

import static com.dongyang.core.global.common.constants.message.FavoriteErrorMessage.NOT_EXIST_FAVORITE_ID_ERROR_MESSAGE;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.repository.FavoriteRepository;
import com.dongyang.core.global.common.exception.model.NotFoundException;
import com.dongyang.core.global.common.utils.MessageUtils;
import com.dongyang.core.global.response.ErrorCode;
import java.util.Optional;

public class FavoriteServiceUtils {

    public static Favorite findFavoriteById(FavoriteRepository favoriteRepository, Long favoriteId) {
        Optional<Favorite> favorite = favoriteRepository.findById(favoriteId);
        return favorite.orElseThrow(() ->
                new NotFoundException(MessageUtils.generate(NOT_EXIST_FAVORITE_ID_ERROR_MESSAGE, favoriteId),
                        ErrorCode.NOT_FOUND_FAVORITE_ERROR));
    }
}
