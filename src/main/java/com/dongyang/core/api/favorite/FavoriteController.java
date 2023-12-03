package com.dongyang.core.api.favorite;

import static com.dongyang.core.global.response.SuccessCode.ADD_FAVORITE_SUCCESS;
import static com.dongyang.core.global.response.SuccessCode.DELETE_FAVORITE_SUCCESS;

import com.dongyang.core.api.favorite.dto.request.ChangeFavoriteStateRequest;
import com.dongyang.core.api.favorite.dto.response.FavoriteFindResponse;
import com.dongyang.core.api.favorite.service.FavoriteRetrieveService;
import com.dongyang.core.api.favorite.service.FavoriteService;
import com.dongyang.core.external.gpt.constant.FunctionType;
import com.dongyang.core.global.common.interceptor.auth.Auth;
import com.dongyang.core.global.common.resolver.MemberId;
import com.dongyang.core.global.response.ApiResponse;
import com.dongyang.core.global.response.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Favorite")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final FavoriteRetrieveService favoriteRetrieveService;

    @Operation(summary = "[인증] 응답 내용 즐겨찾기 상태 변경")
    @Auth
    @PostMapping("/favorite/stateChange")
    public ApiResponse<String> addFavorite(@MemberId Long memberId,
                                           @RequestBody @Valid ChangeFavoriteStateRequest request) {
        favoriteService.addFavorite(memberId, request);
        return ApiResponse.success(ADD_FAVORITE_SUCCESS);
    }

    @Operation(summary = "[인증] 응답 내용 즐겨찾기 삭제")
    @Auth
    @DeleteMapping("/favorite/delete/{favoriteId}")
    public ApiResponse<String> deleteFavorite(@PathVariable final long favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ApiResponse.success(DELETE_FAVORITE_SUCCESS);
    }

    @Operation(summary = "[인증] 기능별 응답 내용 즐겨찾기 조회")
    @Auth
    @GetMapping("/favorite/{functionType}")
    public ApiResponse<FavoriteFindResponse> findFavoritesByFunction(@MemberId final Long memberId,
                                                                     @PathVariable final FunctionType functionType) {
        return ApiResponse.success(
                SuccessCode.FIND_FAVORITES_SUCCESS,
                favoriteRetrieveService.findFavoriteInfosByFunction(memberId, functionType));
    }
}
