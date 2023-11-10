package com.dongyang.core.domain.favorite.repository;

import static com.dongyang.core.domain.favorite.QFavorite.favorite;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.dto.request.ChangeFavoriteStateRequest;
import com.dongyang.core.domain.gpt.constant.FunctionType;
import com.dongyang.core.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Favorite> isExistsFavoriteByAllData(Member member, ChangeFavoriteStateRequest request) {
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

    @Override
    public List<Favorite> findOrderedFavoritesByMemberAndFunction(Member member, FunctionType functionType) {
        return queryFactory
                .selectFrom(favorite)
                .where(
                        favorite.member.eq(member),
                        favorite.functionType.eq(functionType),
                        favorite.isFavorite.eq(true)
                )
                .orderBy(favorite.questionedAt.asc())
                .fetch();
    }
}
