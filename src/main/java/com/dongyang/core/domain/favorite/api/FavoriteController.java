package com.dongyang.core.domain.favorite.api;

import static com.dongyang.core.global.response.SuccessCode.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongyang.core.domain.favorite.dto.request.AddFavoriteRequest;
import com.dongyang.core.domain.favorite.service.FavoriteService;
import com.dongyang.core.global.common.interceptor.auth.Auth;
import com.dongyang.core.global.common.resolver.MemberId;
import com.dongyang.core.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Favorite")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @Operation(summary = "[인증] 응답 내용 즐겨찾기 상태 변경")
    @Auth
    @PostMapping("/favorite/stateChange")
    public ApiResponse<String> addFavorite(@MemberId final Long memberId,
                                           @RequestBody final AddFavoriteRequest request) {
        favoriteService.addFavorite(memberId, request);
        return ApiResponse.success(ADD_FAVORITE_SUCCESS);
    }
}
