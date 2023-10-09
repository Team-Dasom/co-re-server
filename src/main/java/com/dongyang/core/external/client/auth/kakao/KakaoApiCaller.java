package com.dongyang.core.external.client.auth.kakao;

import com.dongyang.core.external.client.auth.kakao.dto.response.KakaoProfileResponse;

public interface KakaoApiCaller {

	KakaoProfileResponse getProfileInfo(String accessToken);

}
