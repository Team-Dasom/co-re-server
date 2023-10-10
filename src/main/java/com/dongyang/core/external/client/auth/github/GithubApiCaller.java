package com.dongyang.core.external.client.auth.github;

import com.dongyang.core.external.client.auth.github.dto.response.GithubProfileResponse;

public interface GithubApiCaller {

	GithubProfileResponse getProfileInfo(String accessToken);

}
