package com.dongyang.core.domain.favorite.service;

import static com.dongyang.core.global.common.constants.message.FavoriteErrorMessage.ALREADY_EXIST_FAVORITE_DATA_ERROR_MESSAGE;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.dto.request.AddFavoriteRequest;
import com.dongyang.core.domain.favorite.repository.FavoriteRepository;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.domain.member.service.MemberServiceUtils;
import com.dongyang.core.global.common.exception.model.ConflictException;
import com.dongyang.core.global.response.ErrorCode;
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

        if(favoriteRepository.isExistsFavoriteByAllData(member, request)) {
            throw new ConflictException(ALREADY_EXIST_FAVORITE_DATA_ERROR_MESSAGE, ErrorCode.CONFLICT_FAVORITE_ERROR);
        }
        favoriteRepository.save(favorite);
    }

}
