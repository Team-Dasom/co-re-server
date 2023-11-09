package com.dongyang.core.domain.favorite.service;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.dto.request.AddFavoriteRequest;
import com.dongyang.core.domain.favorite.repository.FavoriteRepository;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
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

    public void addFavorite(long memberId, AddFavoriteRequest request) {
        Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
        Favorite favorite = Favorite.newInstance(member, request);

        Optional<Favorite> findFavorite = favoriteRepository.isExistsFavoriteByAllData(member, request);
        if (findFavorite.isPresent()) {
            findFavorite.get().changeFavoriteState();
            return;
        }
        favoriteRepository.save(favorite);
    }

}
