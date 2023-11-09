package com.dongyang.core.domain.favorite.repository;

import com.dongyang.core.domain.favorite.dto.request.AddFavoriteRequest;
import com.dongyang.core.domain.member.Member;

public interface FavoriteRepositoryCustom {

    boolean isExistsFavoriteByAllData(Member member, AddFavoriteRequest request);
}
