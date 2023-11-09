package com.dongyang.core.domain.favorite.repository;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.dto.request.AddFavoriteRequest;
import com.dongyang.core.domain.member.Member;
import java.util.Optional;

public interface FavoriteRepositoryCustom {

    Optional<Favorite> isExistsFavoriteByAllData(Member member, AddFavoriteRequest request);
}
