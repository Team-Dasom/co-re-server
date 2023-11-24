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
    //1시간에 3번 사용가능한 무제한 요금제
    MEMBER {
        public Bandwidth getLimit() {
            return Bandwidth.classic(1, Refill.intervally(1, Duration.ofMinutes(1)));
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
