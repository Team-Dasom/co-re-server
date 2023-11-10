package com.dongyang.core.domain.favorite;

import com.dongyang.core.domain.common.BaseEntity;
import com.dongyang.core.domain.favorite.dto.request.ChangeFavoriteStateRequest;
import com.dongyang.core.domain.favorite.dto.response.FavoriteInfoDto;
import com.dongyang.core.domain.gpt.constant.FunctionType;
import com.dongyang.core.domain.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "FAVORITE")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAVORITE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "FUNCTION_TYPE", nullable = false, length = 30)
    private FunctionType functionType;

    @Column(name = "QUESTION", nullable = false)
    private String question;

    @Column(name = "ANSWER", nullable = false)
    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "QUESTIONED_AT", nullable = false)
    private LocalDateTime questionedAt;

    @Column(name = "IS_FAVORITE", nullable = false)
    private Boolean isFavorite;


    public static Favorite newInstance(Member member, ChangeFavoriteStateRequest request) {
        return Favorite.builder()
                .member(member)
                .functionType(request.getFunctionType())
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .questionedAt(request.getQuestionedAt())
                .isFavorite(true)
                .build();
    }

    public FavoriteInfoDto toInfoDto() {
        return FavoriteInfoDto.builder()
                .favoriteId(id)
                .question(question)
                .answer(answer)
                .build();
    }

    public void changeFavoriteState() {
        this.isFavorite = !isFavorite;
    }
}
