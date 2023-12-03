package com.dongyang.core.api.favorite.service;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.api.favorite.dto.request.ChangeFavoriteStateRequest;
import com.dongyang.core.domain.favorite.repository.FavoriteRepository;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.api.member.service.MemberServiceUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {
    private final MemberRepository memberRepository;
    private final FavoriteRepository favoriteRepository;

    public void addFavorite(long memberId, ChangeFavoriteStateRequest request) {
        Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);

        Optional<Favorite> findFavorite = favoriteRepository.isExistsFavoriteByAllData(member, request);
        if (findFavorite.isPresent()) {
            findFavorite.get().changeFavoriteState();
            return;
        }

        favoriteRepository.save(Favorite.newInstance(member, request));
    }

    public void deleteFavorite(long favoriteId) {
        Favorite favorite = FavoriteServiceUtils.findFavoriteById(favoriteRepository, favoriteId);
        favoriteRepository.delete(favorite);
    }
}
