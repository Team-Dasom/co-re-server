package com.dongyang.core.global.scheduler.favorite;

import com.dongyang.core.domain.favorite.Favorite;
import com.dongyang.core.domain.favorite.repository.FavoriteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataManagementScheduler {
    private final FavoriteRepository favoriteRepository;

    @Scheduled(cron = "0 0 3 * * *")
    public void deleteUnusedFavoriteData() {
        List<Favorite> notFavoriteDatum = favoriteRepository.findFavoriteByIsFavorite(false);
        favoriteRepository.deleteAll(notFavoriteDatum);
    }
}
