package com.dongyang.core.domain.favorite.repository;

import static com.dongyang.core.domain.favorite.QFavorite.favorite;
import static com.dongyang.core.domain.member.QMember.member;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.dto.request.AddFavoriteRequest;
import com.dongyang.core.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Favorite> isExistsFavoriteByAllData(Member member, AddFavoriteRequest request) {
        return Optional.ofNullable(queryFactory
                .selectFrom(favorite)
                .where(
                        favorite.member.eq(member),
                        favorite.question.eq(request.getQuestion()),
                        favorite.answer.eq(request.getAnswer()),
                        favorite.questionedAt.eq(request.getQuestionedAt())
                )
                .fetchOne());

    }
}
