package com.dongyang.core.global.common.filter.rateLimit;

import com.dongyang.core.api.member.service.MemberServiceUtils;
import com.dongyang.core.domain.member.Member;
import com.dongyang.core.domain.member.MemberRole;
import com.dongyang.core.domain.member.repository.MemberRepository;
import com.dongyang.core.global.common.exception.model.RateLimitException;
import com.dongyang.core.global.common.utils.JwtUtils;
import com.dongyang.core.global.response.ErrorCode;
import io.github.bucket4j.Bucket;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RateLimitFilter implements Filter {
    private final JwtUtils jwtUtils;
    private final RateLimitBucketProvider bucketProvider;
    private final MemberRepository memberRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorizationHeader = httpRequest.getHeader("Authorization");

        // authorizationHeader가 없는 경우 Filter 동작 생략
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        //authorizationHeader가 있는 경우 JWT AccessToken이 정상적인 경우 token을 사용
        String accessToken = authorizationHeader.substring("Bearer ".length());
        if (jwtUtils.validateToken(accessToken)) {
            Long memberId = jwtUtils.getMemberIdFromJwt(accessToken);
            Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
            //관리자 계정이면 Token 사용X
            if(member.getRole() == MemberRole.ADMIN) {
                log.info("MemberRole이 ADMIN이기 때문에 토큰이 소모되지 않았습니다.");
                chain.doFilter(request, response);
                return;
            }

            Bucket memberBucket = bucketProvider.getBucket(member);
            if (!memberBucket.tryConsume(1)) {
                log.error(String.format("회원ID(%d)의 잔여 토큰이 존재하지 않습니다.", memberId));
                throw new RateLimitException(ErrorCode.TOKEN_NOT_EXIST_EXCEPTION);
            }
            log.info(String.format("회원ID(%d)의 토큰 사용 : 잔여 토큰(%d)", memberId, memberBucket.getAvailableTokens()));
        }

        chain.doFilter(request, response);
    }
}
