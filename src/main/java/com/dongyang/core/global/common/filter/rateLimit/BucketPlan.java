package com.dongyang.core.global.common.filter.rateLimit;

import com.dongyang.core.domain.member.MemberRole;
import com.dongyang.core.global.common.exception.model.IllegalArgumentException;
import com.dongyang.core.global.response.ErrorCode;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import java.time.Duration;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BucketPlan {
    // 총 5개의 토큰, 1분마다 5개의 토큰 충전
    MEMBER {
        public Bandwidth getLimit() {
            return Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));
        }
    };

    public abstract Bandwidth getLimit();

    public static Bandwidth findByMemberRole(MemberRole memberRole) {
        return Arrays.stream(values())
                .filter(v -> v.name().equals(memberRole.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_EXIST_MEMBER_ROLE))
                .getLimit();
    }

}
