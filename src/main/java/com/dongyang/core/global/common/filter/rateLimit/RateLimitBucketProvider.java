package com.dongyang.core.global.common.filter.rateLimit;

import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberRole;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateLimitBucketProvider {
    private Map<Long, Bucket> buckets;

    @PostConstruct
    void initializeBuckets() {
        buckets = new HashMap<>();
    }

    public Bucket getBucket(Member member) {
        long memberId = member.getId();
        if (!buckets.containsKey(memberId)) {
            Bucket memberBucket = createBucketByRole(member.getRole());
            buckets.put(memberId, memberBucket);

            return memberBucket;
        }
        return buckets.get(memberId);
    }

    private Bucket createBucketByRole(MemberRole role) {
        Bandwidth memberLimit = BucketPlan.findByMemberRole(role);
        return Bucket.builder()
                .addLimit(memberLimit)
                .build();
    }
}
