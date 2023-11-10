package com.dongyang.core.domain.favorite.service;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.dto.response.FavoriteFindResponse;
import com.dongyang.core.domain.favorite.repository.FavoriteRepository;
import com.dongyang.core.domain.gpt.constant.FunctionType;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteRetrieveService {
    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;

    public FavoriteFindResponse findFavoriteInfosByFunction(Long memberId, FunctionType functionType) {
        Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
        List<Favorite> favorites = favoriteRepository.findOrderedFavoritesByMemberAndFunction(member,
                functionType);

        return new FavoriteFindResponse(favorites.stream()
                .map(Favorite::toInfoDto)
                .toList());

    }
}
