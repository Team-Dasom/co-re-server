package com.dongyang.core.domain.favorite.repository;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.api.favorite.dto.request.ChangeFavoriteStateRequest;
import com.dongyang.core.external.gpt.constant.FunctionType;
import com.dongyang.core.domain.member.Member;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepositoryCustom {
    Optional<Favorite> isExistsFavoriteByAllData(Member member, ChangeFavoriteStateRequest request);

    List<Favorite> findOrderedFavoritesByMemberAndFunction(Member member, FunctionType functionType);

    List<Favorite> findFavoriteByIsFavorite(boolean isFavorite);
}
