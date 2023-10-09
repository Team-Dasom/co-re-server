package com.dongyang.core.global.common.utils;

import static com.dongyang.core.global.common.constants.message.AuthErrorMessage.*;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.dongyang.core.global.common.constants.auth.JwtKey;
import com.dongyang.core.global.common.constants.auth.RedisKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	private final RedisTemplate<String, Object> redisTemplate;
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;   // 1년
	private static final long REFRESH_TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;    // 1년
	private static final long EXPIRED_TIME = 1L;

	private final Key secretKey;

	public JwtUtils(@Value("${jwt.secret}") String secretKey, RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public List<String> createTokenInfo(Long memberId) {

		long now = (new Date()).getTime();
		Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
		Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

		// Access Token 생성
		String accessToken = Jwts.builder()
			.claim(JwtKey.MEMBER_ID, memberId)
			.setExpiration(accessTokenExpiresIn)
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();

		// Refresh Token 생성
		String refreshToken = Jwts.builder()
			.setExpiration(refreshTokenExpiresIn)
			.signWith(secretKey, SignatureAlgorithm.HS512)
			.compact();

		redisTemplate.opsForValue()
			.set(RedisKey.REFRESH_TOKEN + memberId, refreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

		return List.of(accessToken, refreshToken);
	}

	public void expireRefreshToken(Long memberId) {
		redisTemplate.opsForValue().set(RedisKey.REFRESH_TOKEN + memberId, "", EXPIRED_TIME, TimeUnit.MILLISECONDS);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException | DecodingException e) {
			log.warn(INVALID_JWT_TOKEN_ERROR_MESSAGE, e);
		} catch (ExpiredJwtException e) {
			log.warn(EXPIRED_JWT_TOKEN_ERROR_MESSAGE, e);
		} catch (UnsupportedJwtException e) {
			log.warn(UNSUPPORTED_JWT_TOKEN_ERROR_MESSAGE, e);
		} catch (IllegalArgumentException e) {
			log.warn(EMPTY_CLAIMS_JWT_TOKEN_ERROR_MESSAGE, e);
		} catch (Exception e) {
			log.error(UNHANDLED_JWT_TOKEN_ERROR_MESSAGE, e);
		}
		return false;
	}

	public Long getMemberIdFromJwt(String accessToken) {
		return parseClaims(accessToken).get(JwtKey.MEMBER_ID, Long.class);
	}

	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
